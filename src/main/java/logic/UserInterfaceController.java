package logic;

import java.io.*;
import java.util.List;

public class UserInterfaceController implements GetDecision, GetListOfPath, GetPathable {

    private GetDecision getDecision;
    private GetListOfPath getListOfPath;
    private GetPathable getPathable;
    private GetNumberFromUser getNumberFromUser;

    public UserInterfaceController() {
        this.getDecision = new GetUserDecisionFromConsole();
        this.getListOfPath = new GetListOfPathFromConsole();
        this.getPathable = new GetPathFromConsole();
        this.getNumberFromUser = new GetNumberFromUser();
    }

    public int getUserDecision(GetNumberFromUser getNumberFromUser) {
        return getDecision.getUserDecision(getNumberFromUser);
    }

    public List<File> getListOfPathFromUser(KeyboardReader keyboardReader) {
        return getListOfPath.getListOfPathFromUser(keyboardReader);
    }

    @Override
    public File getPathFromUser() {
        return getPathable.getPathFromUser();
    }
}
