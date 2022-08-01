package logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import data.FileDiffJsonData;
import data.FileDifference;
import data.ResultData;
import logic.ListFileCreator.ListOfFilesFromPathCreator;
import logic.differenceInFilesReader.DifferenceInXmlFilesReader;
import logic.metaDataDifferenceFinder.MetaDataDifferenceFinder;
import logic.metaDataReader.DocxMetaDataReader;
import logic.metaDataReader.MetaDataReadable;
import logic.metaDataReader.PdfMetaDataReader;
import logic.metaDataReader.XlsxMetaDataReader;
import logic.unzip.UnzipFileToDirectoryController;
import logic.unzip.UnzipFileToDirectoryable;
import ui.Messageble;
import ui.UserInterface;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CompareFile implements FileStrategy {
    private final String PDF_FILE = "pdf";
    private final String DOCX_FILE = "docx";
    private final String XLSX_FILE = "xlsx";
    private static final int GREATER_THAN_ZERO = 0;
    private Messageble messageble;
    private UserInterfaceController userInterfaceController;
    private KeyboardReader keyboardReader;
    private UnzipFileToDirectoryable unzipFileToDirectoryable;
    private File sourceFile;
    private File fileToCompare;
    private boolean isRunInConsole;

    public CompareFile(){
        this.messageble = new UserInterface();
        this.keyboardReader = new KeyboardReader(new BufferedReader(new InputStreamReader(System.in)));
        this.userInterfaceController = new UserInterfaceController(new GetFileFromConsole());
        unzipFileToDirectoryable = new UnzipFileToDirectoryController();
        getFilesFromUser();
        isRunInConsole = true;
    }

    public CompareFile(File sourceFile, File fileToCompare) {
        unzipFileToDirectoryable = new UnzipFileToDirectoryController();
        this.sourceFile = sourceFile;
        this.fileToCompare = fileToCompare;
        isRunInConsole = false;

    }

    @Override
    public ResultData perform() {
        List<FileDifference> fileDifferences = new ArrayList<>();
        Map<String, Object> diff;
        String jsonStringWithDifference = "";
        try {
            Path tempDirSource = Files.createTempDirectory("");
            Path tempDirToCompare = Files.createTempDirectory("");
            unzipFileToDirectoryable.unzip(sourceFile.toPath(), tempDirSource);
            unzipFileToDirectoryable.unzip(fileToCompare.toPath(), tempDirToCompare);
            List<File> sourceFiles = new ListOfFilesFromPathCreator().getListOfFile(tempDirSource.toString());
            List<File> filesToCompare = new ListOfFilesFromPathCreator().getListOfFile(tempDirToCompare.toString());
            fileDifferences = new DifferenceInXmlFilesReader().getListOfDifferences(sourceFiles, filesToCompare);
            MetaDataReadable metaDataStrategy = getStrategyToReadMetaData(sourceFile);
            MetaDataDifferenceFinder metaDataDifferenceFinder = new MetaDataDifferenceFinder();
            diff = metaDataDifferenceFinder.getMetaDataDifference(metaDataStrategy.getMataData(sourceFile), metaDataStrategy.getMataData(fileToCompare));
            jsonStringWithDifference = createJson(fileDifferences, diff);
        } catch (IOException ioException) {
            System.out.println(ioException);
        } finally {
            ResultData resultData = new ResultData();
            resultData.setResultMassage("compare-success");
            resultData.setResultData(jsonStringWithDifference);
            return resultData;
        }
    }

    private void getFilesFromUser() {
        messageble.getMessage("add-first-file");
        this.sourceFile = userInterfaceController.getPathFromUser(keyboardReader);
        while (!checkFileExtension(sourceFile, PDF_FILE) && !checkFileExtension(sourceFile, XLSX_FILE) && !checkFileExtension(sourceFile, DOCX_FILE)) {
            messageble.getMessage("wrong-extension");
            this.sourceFile = userInterfaceController.getPathFromUser(keyboardReader);
        }
        String extensionFirstFile = getExtension(sourceFile);
        messageble.getMessage("add-second-file");
        this.fileToCompare = userInterfaceController.getPathFromUser(keyboardReader);
        while (!getExtension(fileToCompare).equals(extensionFirstFile)) {
            messageble.getMessage("compare-file-should-the-same-extension");
            this.fileToCompare = userInterfaceController.getPathFromUser(keyboardReader);
        }
    }

    private Boolean checkFileExtension(File file, String extension) {
        String fileName = file.toString();
        int index = fileName.lastIndexOf('.');
        String fileExtension = fileName.substring(index + 1);
        return fileExtension.equals(extension);
    }

    private String getExtension(File file) {
        String fileName = file.toString();
        int index = fileName.lastIndexOf('.');
        return fileName.substring(index + 1);
    }

    private MetaDataReadable getStrategyToReadMetaData(File file) {
        String extension = getExtension(file);
        MetaDataReadable metaDataReadable = null;
        switch (extension) {
            case "pdf":
                metaDataReadable = new PdfMetaDataReader();
                break;
            case "docx":
                metaDataReadable = new DocxMetaDataReader();
                break;
            case "xlsx":
                metaDataReadable = new XlsxMetaDataReader();
                break;
            default:
        }

        return metaDataReadable;
    }

    private String createJson(List<FileDifference> fileDifferences, Map<String, Object> diff) throws JsonProcessingException {
        FileDiffJsonData fileDiffJsonData = new FileDiffJsonData.Builder()
                .compareDate(new Date())
                .listFileDifference(fileDifferences)
                .mapOfDiffInMetaData(diff)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false);
        return objectMapper.writeValueAsString(fileDiffJsonData);
    }
}
