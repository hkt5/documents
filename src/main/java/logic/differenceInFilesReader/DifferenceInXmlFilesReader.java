package logic.differenceInFilesReader;

import data.FileDifference;
import data.StatusFile;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DifferenceInXmlFilesReader implements DifferenceInFilesReadable{
    private List<FileDifference> listOfFileNameWithDifference;

    @Override
    public List<FileDifference> getListOfDifferences(List<File> sourceFiles, List<File> compareFiles) throws IOException {
        listOfFileNameWithDifference = new ArrayList<>();
        sourceFiles.stream()
                .filter(sourceFile -> fileNameExistInListOfFile(sourceFile, compareFiles))
                .forEach(sourceFile -> {
                    Path pathSourceFile = sourceFile.toPath();
                    Path pathCompareFile = compareFiles.get(getIndex(sourceFile.getName(), compareFiles)).toPath();
                    List<String> list0fFileDifference = diffFiles(pathSourceFile, pathCompareFile);
                    addFileToDifferenceListOrSkip(sourceFile.getName(),list0fFileDifference);
                });

        sourceFiles.stream()
                .filter(sourceFile -> !fileNameExistInListOfFile(sourceFile, compareFiles))
                .forEach(sourceFile -> listOfFileNameWithDifference.add(new FileDifference(sourceFile.getName(), StatusFile.DELETE)));

        compareFiles.stream()
                .filter(fileCompareFiles -> !fileNameExistInListOfFile(fileCompareFiles, sourceFiles))
                .forEach(fileCompareFiles -> listOfFileNameWithDifference.add(new FileDifference(fileCompareFiles.getName(), StatusFile.NEW)));

        return listOfFileNameWithDifference;
    }

    private boolean fileNameExistInListOfFile(File file, List<File> listOfFiles) {
        return listOfFiles.stream()
                .anyMatch(fileInList -> fileInList.getName().equals(file.getName()));
    }

    private void addFileToDifferenceListOrSkip(String fileName, List<String> list0fFileDifference) {
        if (list0fFileDifference.size() > 0) {
            listOfFileNameWithDifference.add(new FileDifference(fileName, StatusFile.CHANGE, list0fFileDifference));
        }
    }

    private int getIndex(String fileName, List<File> listFiles) {
        int index = -1;
        for (int i = 0; i < listFiles.size(); i++) {
            index = i;
            if (listFiles.get(i).getName().equals(fileName)) break;
        }
        return index;
    }

    private static List<String> diffFiles(Path firstFile, Path secondFile) {
        List<String> diff = new ArrayList<>();
        try {
            List<String> firstFileContent = Files.readAllLines(firstFile, Charset.defaultCharset());
            List<String> secondFileContent = Files.readAllLines(secondFile, Charset.defaultCharset());
            firstFileContent.stream()
                    .filter(line -> !secondFileContent.contains(line))
                    .forEach(lineNotExist -> diff.add(lineNotExist));
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
        return diff;
    }
}
