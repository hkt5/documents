package logic;

import java.io.File;

public interface MetaDataAddable {
    boolean addKeywordToMetaData(File file, String hash);
}
