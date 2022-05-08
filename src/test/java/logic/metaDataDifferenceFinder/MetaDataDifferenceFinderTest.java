package logic.metaDataDifferenceFinder;

import logic.ListFileCreator.ListOfPdfXlsxDocxFilesFromPathCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MetaDataDifferenceFinderTest {

    private MetaDataDifferenceFinder metaDataDifferenceFinder;

    @BeforeEach
    public void setup() {
        metaDataDifferenceFinder = new MetaDataDifferenceFinder();
    }

    @Test
    @DisplayName("Should return 3 difference row")
    void getMetaDataDifferenceTest(){
        Map<String, Optional<Object>> source = getTestMap(3, true);
        Map<String, Optional<Object>> compare = getTestMap(3, false);
        Map<String, Object> result = metaDataDifferenceFinder.getMetaDataDifference(source, compare);

        assertEquals(3, result.size(), "Should have 3 difference meta data");
    }

    private Map<String, Optional<Object>> getTestMap(int numberOfRow, boolean numberInValueBefore) {
        HashMap<String, Optional<Object>> testMap = new LinkedHashMap<>();
        int count = 0;
        while (numberOfRow > count) {
            String value = (numberInValueBefore) ? count + "Value" : "Value" + count;
            testMap.put("test" + count, Optional.ofNullable(value));
            count++;
        }
        return testMap;
    }

}