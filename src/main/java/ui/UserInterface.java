package ui;

public class UserInterface extends AbstractInterface implements Messageble{

    @Override
    public void getMessage(String messageName) {
        if (messages.get(messageName) != null) {
            System.out.println(messages.get(messageName));
        } else {
            System.out.println(messageName);
        }

    }
}
