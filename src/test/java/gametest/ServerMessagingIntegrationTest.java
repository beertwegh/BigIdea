package gametest;

import com.google.gson.Gson;
import databaseServer.rest.RestService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import restClient.ToohakGame;
import restClient.host.HostGame;
import restClient.host.IHostGame;
import restClient.host.websocket.IServerWebSocket;
import restClient.host.websocket.ServerWebSocket;
import restClient.host.websocket.messagehandlers.ServerMessageProcessor;
import shared.MultipleChoice;
import shared.websocket.interfaces.IMessageProcessor;
import shared.websocket.interfaces.Message;
import shared.websocket.interfaces.actions.Action;
import shared.websocket.interfaces.actions.AnswerQuestion;
import stubs.AppFlowStack;
import stubs.HostGameStub;
import stubs.ToohakGameStub;

public class ServerMessagingIntegrationTest {



    @Test
    public void AnswerQuestionResultTest() {
        AnswerQuestion answerQuestion = new AnswerQuestion(MultipleChoice.A);
        Message message = new Message(Action.ANSWERQUESTION, answerQuestion);
        Gson gson = new Gson();
        String json = gson.toJson(message);

        IHostGame hostGame = new HostGameStub(new ToohakGameStub());
        ((HostGame) hostGame).setRound(1);
        IMessageProcessor processor = new ServerMessageProcessor(hostGame);
        IServerWebSocket socket = new ServerWebSocket(hostGame);
        socket.setMessageHandler(processor);
        socket.onMessageReceived(json, "1");

        Assert.assertEquals(1, AppFlowStack.getStack().size());
        Assert.assertEquals(MultipleChoice.A.toString(), AppFlowStack.getStack().get(0));
    }
}
