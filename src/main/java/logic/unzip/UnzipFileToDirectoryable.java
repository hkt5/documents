package logic.unzip;

import java.io.IOException;
import java.nio.file.Path;

public interface UnzipFileToDirectoryable {
    void unzip(Path source, Path target) throws IOException;
}
