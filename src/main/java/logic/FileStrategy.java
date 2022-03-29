package logic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileStrategy {
    void perform(List<File> fileList, Path destination) throws IOException;
}
