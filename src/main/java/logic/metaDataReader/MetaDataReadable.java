package logic.metaDataReader;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Map;

public interface MetaDataReadable {
    Map<String, String> getMataData(File file);
}
