package gametests;

import models.Lobby;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import restClient.player.ClientGame;
import restClient.player.IClientGame;
import shared.MultipleChoice;
import stubs.AppFlowStack;
import stubs.ClientMessageGeneratorStub;
import stubs.ToohakGameStub;

public class ClientGameTest {
    private IClientGame game;

    @Before
    public void init() {
        AppFlowStack.clearStack();
    }

    @Test
    public void testJoinLobby() {
        game = new ClientGame(new ToohakGameStub());
        game.setMessageGenerator(new ClientMessageGeneratorStub());
        game.joinLobby(new Lobby("0.0.0.0:1234", "name"));
        Assert.assertEquals(1, AppFlowStack.getStack().size());
        Assert.assertEquals("introduceplayer", AppFlowStack.getStack().get(0));
    }

    @Test
    public void testAnswerQuestion() {
        game = new ClientGame(new ToohakGameStub());
        game.setMessageGenerator(new ClientMessageGeneratorStub());
        game.sendAnswer(MultipleChoice.A);
        Assert.assertEquals(1, AppFlowStack.getStack().size());
        Assert.assertEquals("A", AppFlowStack.getStack().get(0));
    }
}
