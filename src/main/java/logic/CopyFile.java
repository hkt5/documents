package logic;

import org.apache.commons.io.FileUtils;
import ui.Messageble;
import ui.UserInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
                if (isIdentical(file.getAbsolutePath(), destination.getPath() + file.getName())) {
                    System.out.println(file.getName() + "is identical.");
                }
            } catch (IOException ioException) {
                return false;
            }
        }
        return false;
    }

    private boolean isIdentical(String leftFile, String rightFile) {
        try {
            return getMD5ofFile(leftFile).equals(getMD5ofFile(rightFile));
        } catch (IOException ioException) {
            return false;
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            return false;
        }
    }

    private String getMD5ofFile(String file) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        File readFile = new File(file);
        InputStream inputStream = new FileInputStream(readFile);
        byte[] buffer = new byte[8192];
        int read = 0;
        try {
            while ((read = inputStream.read(buffer)) > 0) {
                digest.update(buffer, 0, read);
            }
            byte[] md5sum = digest.digest();
            BigInteger bigInt = new BigInteger(1, md5sum);
            String output = bigInt.toString(16);
            return output;
        } finally {
            inputStream.close();
        }
    }
}
