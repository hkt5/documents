package logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetUserDecisionFromConsoleTest {

    private GetUserDecisionFromConsole getUserDecisionFromConsole;

    @Mock
    GetNumberFromUser getNumberFromUser;

    @BeforeEach
    public void setup(){
        getUserDecisionFromConsole = new GetUserDecisionFromConsole();
    }


    @Test
    @DisplayName("Should return decision from user: 1 or 2")
    public void getUserDecisionTest() throws IOException {
        Mockito.when(getNumberFromUser.getNumberFromUser()).thenReturn(1);
        int aaa = getUserDecisionFromConsole.getUserDecision(getNumberFromUser);
        assertEquals(1, aaa, "Should return list of 5 correct files");
    }


}