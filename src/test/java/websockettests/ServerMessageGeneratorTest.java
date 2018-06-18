package websockettests;

import com.google.gson.Gson;
import models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import restclient.host.websocket.IServerMessageGenerator;
import restclient.host.websocket.ServerMessageGenerator;
import shared.websocket.interfaces.Message;
import shared.websocket.interfaces.actions.Action;
import shared.websocket.interfaces.actions.ReplyAnswerQuestion;
import stubs.AppFlowStack;
import stubs.ServerWebSocketStub;

public class ServerMessageGeneratorTest {
    private IServerMessageGenerator generator;
    private Gson gson;

    @Before
    public void init() {
        AppFlowStack.clearStack();
        generator = new ServerMessageGenerator();
        generator.setSocket(new ServerWebSocketStub());
        gson = new Gson();
    }

    @Test
    public void testStartGame() {
        generator.startGame();
        Message msg = new Message(Action.STARTGAME);
        String msgString = gson.toJson(msg);
        Assert.assertEquals(1, AppFlowStack.getStack().size());
        Assert.assertEquals(msgString, AppFlowStack.getStack().get(0));
    }

    @Test
    public void testNextRound() {
        generator.nextRound();
        Message msg = new Message(Action.NEXTROUND);
        String msgString = gson.toJson(msg);
        Assert.assertEquals(1, AppFlowStack.getStack().size());
        Assert.assertEquals(msgString, AppFlowStack.getStack().get(0));
    }

    @Test
    public void testEndGame() {
        generator.endGame();
        Message msg = new Message(Action.ENDGAME);
        String msgString = gson.toJson(msg);
        Assert.assertEquals(1, AppFlowStack.getStack().size());
        Assert.assertEquals(msgString, AppFlowStack.getStack().get(0));
    }

    @Test
    public void testReplyAnswerQuestionTrue() {
        ReplyAnswerQuestion reply = new ReplyAnswerQuestion(true);
        Message msg = new Message(Action.REPLYANSWERQUESTION, reply);
        String msgString = gson.toJson(msg);
        User user = new User("username", "email", "password");
        generator.replyAnswerQuestion(true, user);
        Assert.assertEquals(1, AppFlowStack.getStack().size());
        Assert.assertEquals(user.getUsername() + msgString, AppFlowStack.getStack().get(0));
    }

    @Test
    public void testReplyAnswerQuestionFalse() {
        ReplyAnswerQuestion reply = new ReplyAnswerQuestion(false);
        Message msg = new Message(Action.REPLYANSWERQUESTION, reply);
        String msgString = gson.toJson(msg);
        User user = new User("username", "email", "password");
        generator.replyAnswerQuestion(false, user);
        Assert.assertEquals(1, AppFlowStack.getStack().size());
        Assert.assertEquals(user.getUsername() + msgString, AppFlowStack.getStack().get(0));

    }
}
