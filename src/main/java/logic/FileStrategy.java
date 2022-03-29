package logic;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileStrategy {
    boolean perform(List<File> fileList, File destination);
}
