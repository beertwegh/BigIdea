package restClient.host;

import Models.Lobby;
import Models.Question;
import Models.User;
import com.google.gson.Gson;
import interfaces.IToohakGame;
import restClient.ToohakGame;
import restClient.host.websocket.IServerMessageGenerator;
import restClient.host.websocket.ServerMessageGenerator;
import restClient.host.websocket.ServerWebSocket;
import restClient.host.websocket.WebSocketServer;
import restClient.host.websocket.messagehandlers.ServerMessageHandler;
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
    ServerWebSocket socket;
    IToohakGame game;
    private IServerMessageGenerator messageGenerator;

    public HostGame(IToohakGame game) {
        this.game = game;
        GetQuestions getQuestions = new GetQuestions();
        questions = (ArrayList<Question>) getQuestions.getQuestions();
        count = 0;
        socket = new ServerWebSocket(this);
        messageGenerator = new ServerMessageGenerator(socket);
    }

    @Override
    public void nextRound() {
        if (questions.get(count) != null) {
            messageGenerator.nextRound();
            count++;
        } else {
            gameEnded();
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
        messageGenerator.startGame();
        nextRound();
    }

    @Override
    public void gameEnded() {
        messageGenerator.endGame();
    }

    @Override
    public void processPlayerJoined(User user) {
        ((ToohakGame) game).addToLobbyList(user);
    }

    @Override
    public void processAnswerQuestion(User user, MultipleChoice answer) {

    }

}