package ui;

public class UserInterface extends AbstractInterface implements Messageble{

    @Override
    public void getMessage(String messageName) {
        System.out.println(messages.get(messageName));
    }
}
