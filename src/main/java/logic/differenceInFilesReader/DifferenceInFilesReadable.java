package logic.differenceInFilesReader;

import data.FileDifference;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface DifferenceInFilesReadable {
    List<FileDifference> getListOfDifferences(List<File> sourceFiles, List<File> compareFiles) throws IOException;
}

