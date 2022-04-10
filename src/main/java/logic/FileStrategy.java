package logic;

import java.io.File;
import java.util.List;

public interface FileStrategy {
    boolean perform(List<File> fileList, File destination);
}
