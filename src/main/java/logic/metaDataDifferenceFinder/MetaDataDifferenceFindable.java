package logic.metaDataDifferenceFinder;

import java.util.Map;
import java.util.Optional;

public interface MetaDataDifferenceFindable {
    Map<String,Object> getMetaDataDifference(Map<String, Optional<Object>> source, Map<String, Optional<Object>> compare);
}
