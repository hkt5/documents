package ui;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

public class UserInterface implements Messageble{

    Map<String, String> messages;

    public UserInterface(){
        try {
            InputStream inputStream = new FileInputStream(new File("./src/main/java/data/messages.yml"));
            Yaml yaml = new Yaml();
            messages = yaml.load(inputStream);
        } catch (Exception e) {
            getMessage("bad-file");
        }
    }

    @Override
    public void getMessage(String messageName) {
        System.out.println(messages.get(messageName));
    }
}
