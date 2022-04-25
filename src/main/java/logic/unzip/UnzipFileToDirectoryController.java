package logic.unzip;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipFileToDirectoryController implements UnzipFileToDirectoryable{
    @Override
    public void unzip(Path source, Path target) throws IOException {
        ZipInputStream zis = new ZipInputStream(new FileInputStream(source.toFile()));
        ZipEntry zipEntry = zis.getNextEntry();

        while (zipEntry != null) {
            boolean isDirectory = isDirectory(zipEntry);
            Path newPath = zipSlipProtect(zipEntry, target);
            copyFiles(isDirectory, newPath, zis);
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
    }

    private boolean isDirectory(ZipEntry zipEntry) {
        if (zipEntry.getName().endsWith(File.separator)) {
            return true;
        } else {
            return false;
        }
    }

    private void copyFiles(Boolean isDirectory, Path newPath, ZipInputStream zis) throws IOException {
        if (isDirectory) {
            Files.createDirectories(newPath);
        } else if (newPath.getParent() != null) {
            if (Files.notExists(newPath.getParent())) {
                Files.createDirectories(newPath.getParent());
            }
            Files.copy(zis, newPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private Path zipSlipProtect(ZipEntry zipEntry, Path targetDir) throws IOException {
        Path targetDirResolved = targetDir.resolve(zipEntry.getName());
        Path normalizePath = targetDirResolved.normalize();
        if (!normalizePath.startsWith(targetDir)) {
            throw new IOException("Bad zip entry: " + zipEntry.getName());
        }
        return normalizePath;
    }
}
