package data;

import java.util.List;

public class FileDifference {
    private String fileName;
    private StatusFile statusFile;
    private List<String> differences;

    public FileDifference(String fileName, StatusFile statusFile) {
        this.fileName = fileName;
        this.statusFile = statusFile;
    }

    public FileDifference(String fileName, StatusFile statusFile, List<String> differences) {
        this.fileName = fileName;
        this.statusFile = statusFile;
        this.differences = differences;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setStatusFile(StatusFile status) {
        statusFile = status;
    }

    public void setDifferences(List<String> differences) {
        this.differences = differences;
    }

    public String getFileName() {
        return fileName;
    }

    public List<String> getDifferences() {
        return differences;
    }

    public StatusFile getStatusFile(){
        return statusFile;
    }
}
