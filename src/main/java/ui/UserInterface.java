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
            InputStream inputStream = new FileInputStream(new File("src/main/data/messages.yml"));
            Yaml yaml = new Yaml();
            messages = yaml.load(inputStream);
        } catch (Exception e) {}
    }

    public static void showInterface() {
        System.out.println("What do you want to do?");
        System.out.println("1. Copy File");
        System.out.println("2. Compare File");
    }

    public static void showMessageAfterBadChoose() {
        System.out.println("Bad choice! You can only choose 1 or 2");
    }

    @Override
    public void getMessage(String messageName) {
        System.out.println(messages.get(messageName));
    }
}
