package logic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class CopyFile implements FileStrategy {
    @Override
    public void perform(List<File> fileList, Path destination) throws IOException {
        for (File file : fileList) {
            Path copied = destination;
            Files.copy(file.toPath(), copied, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
