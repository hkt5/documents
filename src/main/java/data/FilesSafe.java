package data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilesSafe {
    List<File> files;

    public FilesSafe() {
        files = new ArrayList<>();
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public void addFile(File file) {
        files.add(file);
    }
}
