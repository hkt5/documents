package logic.metaDataReader;

import java.io.File;
import java.util.Map;
import java.util.Optional;

public interface MetaDataReadable {
    Map<String, Optional<Object>> getMataData(File file);
}
