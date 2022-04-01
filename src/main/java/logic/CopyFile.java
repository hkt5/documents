package logic;

import org.apache.commons.io.FileUtils;
import ui.Messageble;
import ui.UserInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class CopyFile implements FileStrategy {

    Messageble messageble;
    AddMetadata addMetadata;

    public CopyFile() {
        messageble = new UserInterface();
        addMetadata = new AddMetaDataToPDF();
    }

    @Override
    public boolean perform(List<File> fileList, File destination) {
        try {
            for (File file : fileList) {
                FileUtils.copyFileToDirectory(file, destination);
                if (!isIdentical(file.getAbsolutePath(), destination.getAbsolutePath() + "/" + file.getName())) {
                    return false;
                }
                MessageDigest shaDigest = MessageDigest.getInstance("SHA-512");
                addMetadata.addKeywordToMetaData(new File(destination.getAbsolutePath() + "/" + file.getName()), getFileChecksum(shaDigest, new File(destination.getAbsolutePath() + "/" + file.getName())));
            }
            return true;
        } catch (IOException ioException) {
            return false;
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
    }

    private boolean isIdentical(String leftFile, String rightFile) {
        try {
            MessageDigest shaDigest = MessageDigest.getInstance("SHA-512");
            return getFileChecksum(shaDigest, new File(leftFile)).equals(getFileChecksum(shaDigest, new File(rightFile)));
        } catch (IOException ioException) {
            return false;
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            return false;
        }
    }

    private static String getFileChecksum(MessageDigest digest, File file) throws IOException
    {
        FileInputStream fis = new FileInputStream(file);
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        }
        fis.close();

        byte[] bytes = digest.digest();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < bytes.length; i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
