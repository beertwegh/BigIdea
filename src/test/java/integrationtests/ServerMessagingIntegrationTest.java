package integrationtests;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import restclient.host.IHostGame;
import restclient.host.websocket.IServerWebSocket;
import restclient.host.websocket.ServerWebSocket;
import restclient.host.websocket.messagehandlers.ServerMessageProcessor;
import shared.MultipleChoice;
import shared.websocket.interfaces.IMessageProcessor;
import shared.websocket.interfaces.Message;
import shared.websocket.interfaces.actions.Action;
import shared.websocket.interfaces.actions.AnswerQuestion;
import stubs.AppFlowStack;
import stubs.HostGameStub;

public class ServerMessagingIntegrationTest {

    @Before
    public void init() {
        AppFlowStack.clearStack();
    }

    @Test
    public void AnswerQuestionResultTest() {
        AnswerQuestion answerQuestion = new AnswerQuestion(MultipleChoice.A);
        Message message = new Message(Action.ANSWERQUESTION, answerQuestion);
        Gson gson = new Gson();
        String json = gson.toJson(message);

        IHostGame hostGame = new HostGameStub();
        IMessageProcessor processor = new ServerMessageProcessor(hostGame);
        IServerWebSocket socket = new ServerWebSocket(hostGame);
        socket.setMessageHandler(processor);
        socket.onMessageReceived(json, "1");

        Assert.assertEquals(1, AppFlowStack.getStack().size());
        Assert.assertEquals(MultipleChoice.A.toString(), AppFlowStack.getStack().get(0));
    }
}