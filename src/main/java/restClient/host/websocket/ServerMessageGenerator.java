package restClient.host.websocket;

import Models.User;
import com.google.gson.Gson;
import shared.websocket.interfaces.Message;
import shared.websocket.interfaces.actions.Action;

public class ServerMessageGenerator implements IServerMessageGenerator {
    private IServerWebSocket socket;

    public ServerMessageGenerator(IServerWebSocket socket) {
        this.socket = socket;
    }

    @Override
    public void startGame() {
        socket.broadcast("Game is starting");
    }

    @Override
    public void nextRound() {
        Message message = new Message(Action.NEXTROUND);
        Gson gson = new Gson();
        String json = gson.toJson(message);
        socket.broadcast(json);
    }

    @Override
    public void endGame() {
        socket.broadcast("game has ended");
    }

    @Override
    public void replyAnswerQuestion(boolean correct, User user) {
        //TODO
    }
}