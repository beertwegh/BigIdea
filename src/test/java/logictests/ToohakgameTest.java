package logictests;

import Models.User;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import interfaces.IToohakGame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import restClient.ToohakGame;
import userinterface.Controller;

public class ToohakgameTest {
    private IToohakGame game;

    @Before
    public void init() {
        game = new ToohakGame(new Controller());
    }

    @Test
    public void testLogin() {
        String result = game.registerPlayer("usertest", "passtest", "test@gmail.com");
        Gson gson = new Gson();
        try {
            gson.fromJson(result, User.class);
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }
}
