package websockettests;

import com.google.gson.Gson;
import models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import restClient.player.websocket.ClientMessageGenerator;
import restClient.player.websocket.IClientMessageGenerator;
import shared.MultipleChoice;
import shared.websocket.interfaces.Message;
import shared.websocket.interfaces.actions.Action;
import shared.websocket.interfaces.actions.AnswerQuestion;
import shared.websocket.interfaces.actions.IntroduceUser;
import stubs.AppFlowStack;
import stubs.ClientWebSocketStub;

public class ClientMessageGeneratorTest {
    private IClientMessageGenerator generator;
    private Gson gson;

    @Before
    public void init() {
        AppFlowStack.clearStack();
        gson = new Gson();
        generator = new ClientMessageGenerator();
        generator.setSocket(new ClientWebSocketStub());
    }

    @Test
    public void testIntroducePlayer() {
        User user = new User("username", "email", "password");
        IntroduceUser introduceUser = new IntroduceUser(user);
        Message message = new Message(Action.INTRODUCEUSER, introduceUser);
        String msgString = gson.toJson(message);
        generator.introducePlayer(user);
        Assert.assertEquals(1, AppFlowStack.getStack().size());
        Assert.assertEquals(msgString, AppFlowStack.getStack().get(0));
    }

    @Test
    public void testAnswerQuestion() {
        AnswerQuestion answerQuestion = new AnswerQuestion(MultipleChoice.A);
        Message message = new Message(Action.ANSWERQUESTION, answerQuestion);
        String msgString = gson.toJson(message);
        generator.answerQuestion(MultipleChoice.A);

        Assert.assertEquals(1, AppFlowStack.getStack().size());
        Assert.assertEquals(msgString, AppFlowStack.getStack().get(0));
    }
}