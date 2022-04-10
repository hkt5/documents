package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface MetaDataAddable {
    boolean addKeywordToMetaData(File file, String hash);
}
