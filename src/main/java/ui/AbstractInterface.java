package ui;

import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

public abstract class AbstractInterface {

    Map<String, String> messages;

    public AbstractInterface() {
        try {
            InputStream inputStream = new FileInputStream(new File("./src/main/java/data/messages.yml"));
            Yaml yaml = new Yaml();
            messages = yaml.load(inputStream);
        } catch (Exception e) {
            System.out.println("Failed to load the file messages.yml");
        }
    }
}
