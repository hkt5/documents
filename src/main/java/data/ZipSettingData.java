package data;

import java.nio.file.Path;
import java.util.zip.ZipInputStream;

public class ZipSettingData {
    private Boolean isDirectory;
    private Path newPath;
    private ZipInputStream zis;

    public Boolean getDirectory() {
        return isDirectory;
    }

    public Path getNewPath() {
        return newPath;
    }

    public ZipInputStream getZis() {
        return zis;
    }

    public void setDirectory(Boolean directory) {
        isDirectory = directory;
    }

    public void setNewPath(Path newPath) {
        this.newPath = newPath;
    }

    public void setZis(ZipInputStream zis) {
        this.zis = zis;
    }
}
