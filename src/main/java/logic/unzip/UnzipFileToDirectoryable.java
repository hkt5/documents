package logic.unzip;

import java.nio.file.Path;

public interface UnzipFileToDirectoryable {
    void unzip(Path source, Path target);
}
