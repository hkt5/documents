package data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Files {
    List<File> files;

    public Files() {
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
