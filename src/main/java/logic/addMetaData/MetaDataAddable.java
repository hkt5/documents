package logic.addMetaData;

import java.io.File;

public interface MetaDataAddable {
    boolean addKeywordToMetaData(File file, String hash);
}
