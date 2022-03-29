package logic;

import java.io.*;
import java.util.List;

public class UserInterfaceController implements GetDecision, GetListOfPath, GetPathable {

    private GetDecision getDecision;
    private GetListOfPath getListOfPath;
    private GetPathable getPathable;

    public UserInterfaceController() {
        this.getDecision = new GetUserDecisionFromConsole();
        this.getListOfPath = new GetListOfPathFromConsole();
        this.getPathable = new GetPathFromConsole();
    }

    public int getUserDecision() {
        return getDecision.getUserDecision();
    }

    public List<File> getListOfPathFromUser() {
        return getListOfPath.getListOfPathFromUser();
    }

    @Override
    public File getPathFromUser() {
        return getPathable.getPathFromUser();
    }
}
