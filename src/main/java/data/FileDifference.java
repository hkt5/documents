package data;

import java.util.List;

public class FileDifference {
    private String fileName;
    private StatusFile statusFile;
    private List<String> differences;


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
