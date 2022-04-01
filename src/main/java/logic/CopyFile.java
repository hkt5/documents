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
    MetaDataAddable metaDataAddable;

    public CopyFile() {
        messageble = new UserInterface();
        metaDataAddable = new AddMetaDataToPDF();
    }

    @Override
    public boolean perform(List<File> fileList, File destination) {
        try {
            for (File file : fileList) {
                FileUtils.copyFileToDirectory(file, destination);
                if (!isIdentical(file.getAbsolutePath(), destination.getAbsolutePath() + "/" + file.getName())) {
                    return false;
                }
                String hashCopedFile = getFileChecksum(getMessageDigest(), new File(destination.getAbsolutePath() + "/" + file.getName()));
                File copiedFile = new File(destination.getAbsolutePath() + "/" + file.getName());
                metaDataAddable.addKeywordToMetaData(copiedFile, hashCopedFile);
            }
            return true;
        } catch (IOException | NoSuchAlgorithmException exception) {
            return false;
        }
    }

    private MessageDigest getMessageDigest() throws NoSuchAlgorithmException {
        MessageDigest shaDigest = MessageDigest.getInstance("SHA-512");
        return shaDigest;
    }

    private boolean isIdentical(String leftFile, String rightFile) {
        try {

            return getFileChecksum(getMessageDigest(), new File(leftFile)).equals(getFileChecksum(getMessageDigest(), new File(rightFile)));
        } catch (IOException | NoSuchAlgorithmException exception) {
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
