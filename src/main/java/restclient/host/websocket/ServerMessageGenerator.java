package restclient.host.websocket;

import com.google.gson.Gson;
import models.User;
import shared.websocket.interfaces.Message;
import shared.websocket.interfaces.actions.Action;
import shared.websocket.interfaces.actions.ReplyAnswerQuestion;

public class ServerMessageGenerator implements IServerMessageGenerator {
    private IServerWebSocket socket;
    private Gson gson;

    public ServerMessageGenerator() {
        gson = new Gson();

    }

    @Override
    public void setSocket(IServerWebSocket socket) {
        this.socket = socket;
    }

    @Override
    public void startGame() {
        Message message = new Message(Action.STARTGAME);
        String json = gson.toJson(message);
        socket.broadcast(json);
    }

    @Override
    public void nextRound() {
        Message message = new Message(Action.NEXTROUND);
        String json = gson.toJson(message);
        socket.broadcast(json);
    }

    @Override
    public void endGame() {
        Message message = new Message(Action.ENDGAME);
        String json = gson.toJson(message);
        socket.broadcast(json);
    }

    @Override
    public void replyAnswerQuestion(boolean correct, User user) {
        ReplyAnswerQuestion reply = new ReplyAnswerQuestion(correct);
        Message message = new Message(Action.REPLYANSWERQUESTION, reply);
        String json = gson.toJson(message);
        socket.sendToUser(user, json);
    }
}