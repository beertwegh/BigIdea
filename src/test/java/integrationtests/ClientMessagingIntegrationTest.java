package integrationtests;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import restClient.player.ClientGame;
import restClient.player.IClientGame;
import restClient.player.websocket.ClientWebSocket;
import restClient.player.websocket.IClientWebSocket;
import restClient.player.websocket.messagehandlers.ClientMessageProcessor;
import shared.websocket.interfaces.IMessageProcessor;
import shared.websocket.interfaces.Message;
import shared.websocket.interfaces.actions.Action;
import shared.websocket.interfaces.actions.ReplyAnswerQuestion;
import shared.websocket.interfaces.actions.StartGame;
import stubs.AppFlowStack;
import stubs.ToohakGameStub;

public class ClientMessagingIntegrationTest {

    private IClientGame clientGame;
    private IMessageProcessor processor;
    private IClientWebSocket socket;

    @Before
    public void init() {
        AppFlowStack.clearStack();
        clientGame = new ClientGame(new ToohakGameStub());
        processor = new ClientMessageProcessor(clientGame);
        socket = new ClientWebSocket();
        socket.setMessageHandler(processor);
    }

    @Test
    public void AnswerQuestionResponseTest() {
        ReplyAnswerQuestion reply = new ReplyAnswerQuestion(true);
        Message message = new Message(Action.REPLYANSWERQUESTION, reply);
        Gson gson = new Gson();
        String json = gson.toJson(message);

        socket.onWebSocketMessageReceived(json, "1");

        Assert.assertEquals(1, AppFlowStack.getStack().size());
        Assert.assertEquals("true", AppFlowStack.getStack().get(0));
    }

    @Test
    public void StartGameResponseTest() {
        StartGame startGame = new StartGame();
        Message message = new Message(Action.STARTGAME, startGame);
        Gson gson = new Gson();
        String json = gson.toJson(message);

        socket.onWebSocketMessageReceived(json, "1");

        Assert.assertEquals(1, AppFlowStack.getStack().size());
        Assert.assertEquals("startgame", AppFlowStack.getStack().get(0));
    }

    @Test
    public void NextRoundResponseTest() {
        Message message = new Message(Action.NEXTROUND);
        Gson gson = new Gson();
        String json = gson.toJson(message);

        socket.onWebSocketMessageReceived(json, "1");

        Assert.assertEquals(1, AppFlowStack.getStack().size());
        Assert.assertEquals("nextround", AppFlowStack.getStack().get(0));
    }
    @Test
    public void EndGameResponseTest() {
        Message message = new Message(Action.ENDGAME);
        Gson gson = new Gson();
        String json = gson.toJson(message);

        socket.onWebSocketMessageReceived(json, "1");

        Assert.assertEquals(1, AppFlowStack.getStack().size());
        Assert.assertEquals("endgame", AppFlowStack.getStack().get(0));
    }
}