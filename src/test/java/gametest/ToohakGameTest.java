package gametest;

import Models.Lobby;
import interfaces.IToohakGame;
import org.junit.Before;
import org.junit.Test;
import restClient.ToohakGame;
import userinterface.Controller;

public class ToohakGameTest {
    private IToohakGame toohakGame;

    @Before
    public void init() {
        toohakGame = new ToohakGame(new Controller());
    }

}
