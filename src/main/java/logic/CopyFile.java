package logic;

import org.apache.commons.io.FileUtils;
import ui.Messageble;
import ui.UserInterface;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CopyFile implements FileStrategy {

    Messageble messageble;

    public CopyFile() {
        messageble = new UserInterface();
    }

    @Override
    public boolean perform(List<File> fileList, File destination) {
        for (File file : fileList) {
            try {
                FileUtils.copyFileToDirectory(file, destination);
            } catch (IOException ioException) {
                return false;
            }
        }
        return true;
    }
}
