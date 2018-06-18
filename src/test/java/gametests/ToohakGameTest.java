package gametests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import restClient.ToohakGame;
import stubs.AppFlowStack;
import stubs.ClientGameStub;
import stubs.HostGameStub;
import userinterface.Controller;

public class ToohakGameTest {
    private ToohakGame game;

    @Before
    public void init() {
        AppFlowStack.clearStack();
        game = new ToohakGame(new Controller());
    }

    @Test
    public void testNextRoundHost() {
        game.chooseHostOrClient(new HostGameStub());
        game.nextRound();
        Assert.assertEquals(1, AppFlowStack.getStack().size());
        Assert.assertEquals("nextround", AppFlowStack.getStack().get(0));
    }

    @Test
    public void testNextRoundClient() {
        game.chooseHostOrClient(new ClientGameStub());
        game.nextRound();
        Assert.assertEquals(1, AppFlowStack.getStack().size());
        Assert.assertEquals("nextround", AppFlowStack.getStack().get(0));
    }


}
