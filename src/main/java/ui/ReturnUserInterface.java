package ui;

public class ReturnUserInterface extends AbstractInterface implements MessageReturnable{

    @Override
    public String getMassage(String messageName) {
        return messages.get(messageName);
    }
}
