package logic;

import java.io.File;

public interface AddMetadata {
    boolean addKeywordToMetaData(File file, String hash);
}
