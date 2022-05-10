package logic;

import data.ResultData;
import logic.addMetaData.AddMetaDataToDocx;
import logic.addMetaData.AddMetaDataToPDF;
import logic.addMetaData.AddMetaDataToXlsx;
import logic.addMetaData.MetaDataAddable;
import org.apache.commons.io.FileUtils;
import ui.MessageReturnable;
import ui.ReturnUserInterface;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class CopyFile implements FileStrategy {
    public static final String FILES_NOT_COPIED = "files-not-copied";
    public static final String FILES_COPIED = "files-copied";
    private final int GREATER_THAN_ZERO = 0;
    private final String PDF_FILE = "pdf";
    private final String DOCX_FILE = "docx";
    private final String XLSX_FILE = "xlsx";
    private MessageReturnable messageReturnable;
    private UserInterfaceController userInterfaceController;
    private KeyboardReader keyboardReader;
    private List<File> fileList;
    private File destination;

    public CopyFile() {
        this.messageReturnable = new ReturnUserInterface();
        this.keyboardReader = new KeyboardReader(new BufferedReader(new InputStreamReader(System.in)));
        this.userInterfaceController = new UserInterfaceController();
        this.fileList = new ArrayList<>();
        fileList = userInterfaceController.getListOfPathFromUser(keyboardReader);
        destination = userInterfaceController.getPathFromUser(keyboardReader);
    }

    public CopyFile(List<File> fileList, File destination) {
        this.messageReturnable = new ReturnUserInterface();
        this.keyboardReader = new KeyboardReader(new BufferedReader(new InputStreamReader(System.in)));
        this.userInterfaceController = new UserInterfaceController();
        this.fileList = fileList;
        this.destination = destination;
    }

    @Override
    public ResultData perform() {
        ResultData resultData = new ResultData();
        for (File file : fileList) {
            File copiedFile = new File(destination.getAbsolutePath() + "/" + file.getName());
            if (!copyFile(file, destination)) {
                resultData.setResultMassage(FILES_NOT_COPIED);
            } else if (!addHash(copiedFile)) {
                resultData.setResultMassage(FILES_NOT_COPIED);
            }
        }
        resultData.setResultMassage(FILES_COPIED);
        return resultData;
    }

    private boolean copyFile(File file, File destination) {
        try {
            FileUtils.copyFileToDirectory(file, destination);
            return isIdentical(file.getAbsolutePath(), destination.getAbsolutePath() + "/" + file.getName());
        } catch (IOException ioException) {
            return false;
        }
    }

    private boolean addHash(File file) {
        String hashCopedFile;
        try {
            hashCopedFile = getFileChecksum(getMessageDigest(), file);
        } catch (NoSuchAlgorithmException | IOException e) {
            return false;
        }
        MetaDataAddable metaDataAddable;
        if (checkIfParameterFileHasParameterExtension(file, PDF_FILE)) {
            metaDataAddable = new AddMetaDataToPDF();
            metaDataAddable.addKeywordToMetaData(file,hashCopedFile);
            return true;
        } else if (checkIfParameterFileHasParameterExtension(file, XLSX_FILE)) {
            metaDataAddable = new AddMetaDataToXlsx();
            metaDataAddable.addKeywordToMetaData(file,hashCopedFile);
            return true;
        } else if (checkIfParameterFileHasParameterExtension(file, DOCX_FILE)) {
            metaDataAddable = new AddMetaDataToDocx();
            metaDataAddable.addKeywordToMetaData(file,hashCopedFile);
            return true;
        } else {
            return false;
        }
    }

    private Boolean checkIfParameterFileHasParameterExtension(File file, String extension) {
        String fileName = file.toString();
        int index = fileName.lastIndexOf('.');
        String fileExtension = fileName.substring(index + 1);
        if (fileExtension.equals(extension)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    private MessageDigest getMessageDigest() throws NoSuchAlgorithmException {
        MessageDigest shaDigest = MessageDigest.getInstance("SHA-512");
        return shaDigest;
    }

    private boolean isIdentical(String leftFile, String rightFile) {
        try {
            return getFileChecksum(getMessageDigest(), new File(leftFile)).equals(getFileChecksum(getMessageDigest(), new File(rightFile)));
        } catch (IOException | NoSuchAlgorithmException exception) {
            return false;
        }
    }

    private static String getFileChecksum(MessageDigest digest, File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        }
        fis.close();

        byte[] bytes = digest.digest();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
