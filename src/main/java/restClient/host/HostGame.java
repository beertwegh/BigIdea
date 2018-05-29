package restClient.host;

import Models.Lobby;
import Models.Question;
import Models.User;
import com.google.gson.Gson;
import databaseServer.datacontext.LobbyDataContext;
import databaseServer.datacontext.QuestionDataContext;
import databaseServer.repositories.ILobbyRepository;
import databaseServer.repositories.IQuestionRepository;
import databaseServer.repositories.LobbyRepository;
import databaseServer.repositories.QuestionRepository;
import interfaces.IToohakGame;
import restClient.ToohakGame;
import restClient.host.websocket.ServerWebSocket;
import restClient.host.websocket.WebSocketServer;
import shared.Logging.Logger;
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
        IQuestionRepository repository = new QuestionRepository(new QuestionDataContext());
        questions = (ArrayList<Question>) repository.findAll();
        count = 0;
    }

    @Override
    public void nextRound() {
        if (questions.get(count) != null) {
            NextRound nextRound = new NextRound(questions.get(count));
            Message message = new Message("NextRound", nextRound);
            Gson gson = new Gson();
            String json = gson.toJson(message);
            socket.broadcast(json);
            count++;
        } else {
            socket.broadcast("no more questions, game is over!");
        }
    }


    @Override
    public boolean createLobby(Lobby lobby) {
        try {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    WebSocketServer.startWebSocketServer(socket);
                }
            });
            thread.start();
            ILobbyRepository repository = new LobbyRepository(new LobbyDataContext());
            repository.save(lobby);
            return true;
        } catch (Exception ex) {

            Logger.getInstance().log(ex);
            return false;
        }
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
}