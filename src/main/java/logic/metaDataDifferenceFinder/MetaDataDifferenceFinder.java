package logic.metaDataDifferenceFinder;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MetaDataDifferenceFinder implements MetaDataDifferenceFindable{
    @Override
    public Map<String, Object> getMetaDataDifference(Map<String, Optional<Object>> source, Map<String, Optional<Object>> compare) {
        return compare.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(),
                        e -> e.getValue().equals(source.get(e.getKey())) ? null : e.getValue()));
    }
}
