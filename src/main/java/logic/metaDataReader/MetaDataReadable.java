package logic.metaDataReader;

import data.MetaData;

import java.io.File;

public interface MetaDataReadable {
    MetaData getMataData(File file);
}
