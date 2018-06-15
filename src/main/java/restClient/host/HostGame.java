package restClient.host;

import Models.Lobby;
import Models.Question;
import Models.User;
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
    private int round;
    ServerWebSocket socket;
    IToohakGame game;
    private IServerMessageGenerator messageGenerator;
    private RandomFisher random;

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getRound() {
        return round;
    }

    public HostGame(IToohakGame game) {
        this.game = game;
        GetQuestions getQuestions = new GetQuestions();
        questions = (ArrayList<Question>) getQuestions.getQuestions();
        round = 0;
        socket = new ServerWebSocket(this);
        messageGenerator = new ServerMessageGenerator(socket);
        random = new RandomFisher(questions.size());
    }

    @Override
    public void nextRound() {
        if (questions.get(random.next()) != null) {
            messageGenerator.nextRound();
            game.processNextRound(this);
            round++;
        } else {
            gameEnded();
        }
    }

    @Override
    public String createLobby(Lobby lobby) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String[] split = lobby.getIp().split(":");
                String portString = split[1];
                int port = Integer.parseInt(portString);
                WebSocketServer.startWebSocketServer(socket, port);
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
        boolean correct = questions.get(round - 1).getAnswers().get(answer.getValue()).isCorrect();
        if (correct) {
            messageGenerator.replyAnswerQuestion(true, user);
            SetHighScoreAction action = new SetHighScoreAction();
            action.setHighScore(new SetHighScore(user.getId(), user.getScore() + 10));
        }
    }
}