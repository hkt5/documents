package logic.metaDataDifferenceFinder;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MetaDataDifferenceFinder implements MetaDataDifferenceFindable{

    @Override
    public Map<String, Object> getMetaDataDifference(Map<String, Optional<Object>> source, Map<String, Optional<Object>> compare) {
        return compare.entrySet().stream()
                .filter(entry -> entry.getValue().isPresent())
                .filter(entry -> entry.getValue().get().equals(source.get(entry.getKey()).get()) != true)
                .collect(Collectors.toMap(key -> key.getKey(), value -> value.getValue().get()));
    }
}
