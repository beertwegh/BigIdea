package restClient.host;

import Models.Lobby;
import Models.Question;
import Models.User;
import com.google.gson.Gson;
import interfaces.IToohakGame;
import restClient.ToohakGame;
import restClient.host.websocket.ServerWebSocket;
import restClient.host.websocket.WebSocketServer;
import restClient.restActions.GetQuestions;
import restClient.restActions.SaveLobby;
import shared.MultipleChoice;
import shared.websocket.interfaces.actions.Action;
import shared.websocket.interfaces.Message;
import shared.websocket.interfaces.actions.NextRound;

import java.util.ArrayList;

public class HostGame implements IHostGame {

    private ArrayList<Question> questions;
    private int count;
    ServerWebSocket socket = new ServerWebSocket(this);
    IToohakGame game;

    public HostGame(IToohakGame game) {
        this.game = game;
        GetQuestions getQuestions = new GetQuestions();
        questions = (ArrayList<Question>) getQuestions.getQuestions();
        count = 0;
    }

    @Override
    public void nextRound() {
        if (questions.get(count) != null) {
            NextRound nextRound = new NextRound();
            Message message = new Message(Action.NEXTROUND);
            Gson gson = new Gson();
            String json = gson.toJson(message);
            socket.broadcast(json);
            count++;
        } else {
            socket.broadcast("no more questions, game is over!");
        }
    }


    @Override
    public String createLobby(Lobby lobby) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                WebSocketServer.startWebSocketServer(socket);
            }
        });
        thread.start();
        SaveLobby saveLobby = new SaveLobby();
        return saveLobby.saveLobby(lobby);
    }

    @Override
    public void startGame() {
        socket.broadcast("Game is starting");
        nextRound();
    }

    @Override
    public void gameEnded() {

    }

    @Override
    public void processPlayerJoined(User user) {
        ((ToohakGame) game).addToLobbyList(user);
    }

    @Override
    public void processAnswerQuestion(User user, MultipleChoice answer) {

    }

}