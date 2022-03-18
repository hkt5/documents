package model;

import java.io.File;

public class UserOptions {
    private int strategy;
    private File file;

    public int getStrategy() {
        return strategy;
    }

    public File getFile() {
        return file;
    }

    public void setStrategy(int strategy) {
        this.strategy = strategy;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
