package restClient.host;

import models.Lobby;
import models.Question;
import models.User;
import interfaces.IToohakGame;
import restClient.ToohakGame;
import restClient.host.websocket.IServerMessageGenerator;
import restClient.host.websocket.ServerMessageGenerator;
import restClient.host.websocket.ServerWebSocket;
import restClient.host.websocket.WebSocketServer;
import restClient.restActions.GetQuestions;
import restClient.restActions.SaveLobby;
import restClient.restActions.SetHighScoreAction;
import shared.MultipleChoice;
import shared.restrequest.SetHighScore;
import utilities.RandomFisher;

import java.util.ArrayList;

public class HostGame implements IHostGame {

    private ArrayList<Question> questions;
    private ServerWebSocket socket;
    private IToohakGame game;
    private IServerMessageGenerator messageGenerator;
    private RandomFisher random;
    private Integer currentRandom;

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public int getCurrentRandom() {
        return currentRandom;
    }

    public HostGame(IToohakGame game) {
        this.game = game;
        GetQuestions getQuestions = new GetQuestions();
        questions = (ArrayList<Question>) getQuestions.getQuestions();
        socket = new ServerWebSocket(this);
        messageGenerator = new ServerMessageGenerator(socket);
        random = new RandomFisher(questions.size());
    }

    @Override
    public void nextRound() {
        currentRandom = random.next();
        if (currentRandom != null) {
            messageGenerator.nextRound();
            game.processNextRound(this);
        } else
            gameEnded();
    }

    @Override
    public String createLobby(Lobby lobby) {

        Thread thread = new Thread(() -> {
            String[] split = lobby.getIp().split(":");
            String portString = split[1];
            int port = Integer.parseInt(portString);
            WebSocketServer.startWebSocketServer(socket, port);
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
        game.processEndGame(this);
    }

    @Override
    public void processPlayerJoined(User user) {
        ((ToohakGame) game).addToLobbyList(user);
    }

    @Override
    public void processAnswerQuestion(User user, MultipleChoice answer) {
        boolean correct = questions.get(currentRandom).getAnswers().get(answer.getValue()).isCorrect();
        if (correct) {
            messageGenerator.replyAnswerQuestion(true, user);
            SetHighScoreAction action = new SetHighScoreAction();
            action.setHighScore(new SetHighScore(user.getId(), user.getScore() + 10));
        } else {
            messageGenerator.replyAnswerQuestion(false, user);
        }
    }
}