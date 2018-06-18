package integrationtests;

import com.google.gson.Gson;
import models.User;
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
import shared.websocket.interfaces.actions.IntroduceUser;
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

    @Test
    public void TestIntroduceUser() {
        User user = new User(1, "username", "email", 10, "password");
        IntroduceUser introduceUser = new IntroduceUser(user);
        Message message = new Message(Action.INTRODUCEUSER, introduceUser);
        Gson gson = new Gson();
        String json = gson.toJson(message);
        IHostGame hostGame = new HostGameStub();
        ServerMessageProcessor processor = new ServerMessageProcessor(hostGame);
        IServerWebSocket socket = new ServerWebSocket(hostGame);
        socket.setMessageHandler(processor);
        socket.onMessageReceived(json, "1");

        User user1 = processor.getUserBySessionId("1");
        Assert.assertEquals(user1.getId(), user.getId());
        Assert.assertEquals(user1.getUsername(), user.getUsername());
        Assert.assertEquals(user1.getEmail(), user.getEmail());
        Assert.assertEquals(user1.getPassword(), user.getPassword());
        Assert.assertEquals(user1.getScore(), user.getScore());
        Assert.assertEquals(1, AppFlowStack.getStack().size());
        Assert.assertEquals(user.getUsername(), AppFlowStack.getStack().get(0));
    }
}