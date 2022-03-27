package logic;

import java.io.*;
import java.util.List;

public class UserInterfaceController implements GetDecision, GetListOfPath {

    private GetDecision getDecision;
    private GetListOfPath getListOfPath;

    public UserInterfaceController() {
        this.getDecision = new GetUserDecisionFromConsole();
        this.getListOfPath = new GetListOfPathFromConsole();
    }

    public int getUserDecision() {
        return getDecision.getUserDecision();
    }

    public List<File> getListOfPathFromUser() {
        return getListOfPath.getListOfPathFromUser();
    }
}
