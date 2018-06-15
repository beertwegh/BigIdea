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
import stubs.AppFlowStack;
import stubs.ToohakGameStub;

public class ClientMessagingIntegrationTest {
    @Before
    public void init() {
        AppFlowStack.clearStack();
    }

    @Test
    public void AnswerQuestionResponseTest() {
        ReplyAnswerQuestion reply = new ReplyAnswerQuestion(true);
        Message message = new Message(Action.REPLYANSWERQUESTION, reply);
        Gson gson = new Gson();
        String json = gson.toJson(message);

        IClientGame clientGame = new ClientGame(new ToohakGameStub());
        IMessageProcessor processor = new ClientMessageProcessor(clientGame);
        IClientWebSocket socket = new ClientWebSocket();
        socket.setMessageHandler(processor);
        socket.onWebSocketMessageReceived(json, "1");

        Assert.assertEquals(1, AppFlowStack.getStack().size());
        Assert.assertEquals("true", AppFlowStack.getStack().get(0));

    }
}
