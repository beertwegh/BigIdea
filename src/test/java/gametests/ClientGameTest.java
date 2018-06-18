package gametests;

import models.Lobby;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import restclient.player.ClientGame;
import restclient.player.IClientGame;
import shared.MultipleChoice;
import stubs.AppFlowStack;
import stubs.ClientMessageGeneratorStub;
import stubs.ToohakGameStub;

public class ClientGameTest {
    private IClientGame game;

    @Before
    public void init() {
        AppFlowStack.clearStack();
        game = new ClientGame();
        game.setIToohakGame(new ToohakGameStub());
    }

    @Test
    public void testJoinLobby() {
        game.setMessageGenerator(new ClientMessageGeneratorStub());
        game.joinLobby(new Lobby("0.0.0.0:1234", "name"));
        Assert.assertEquals(1, AppFlowStack.getStack().size());
        Assert.assertEquals("introduceplayer", AppFlowStack.getStack().get(0));
    }

    @Test
    public void testAnswerQuestion() {
        game.setMessageGenerator(new ClientMessageGeneratorStub());
        game.sendAnswer(MultipleChoice.A);
        Assert.assertEquals(1, AppFlowStack.getStack().size());
        Assert.assertEquals("A", AppFlowStack.getStack().get(0));
    }
}
