package restclient;

import com.google.gson.Gson;
import databaseserver.rest.response.LoginResponse;
import interfaces.IGame;
import interfaces.IToohakGame;
import models.Lobby;
import models.User;
import restclient.host.HostGame;
import restclient.host.IHostGame;
import restclient.host.websocket.ServerMessageGenerator;
import restclient.player.IClientGame;
import restclient.player.websocket.ClientMessageGenerator;
import restclient.restactions.ClearLobbiesAction;
import restclient.restactions.LoginAction;
import restclient.restactions.RegisterAction;
import shared.logging.Logger;
import shared.MultipleChoice;
import shared.restrequest.ClearLobbies;
import shared.restrequest.Login;
import shared.restrequest.Register;
import userinterface.Controller;

import java.util.List;

public class ToohakGame implements IToohakGame {

    private IGame game;
    private Controller application;
    private User user;

    public User getUser() {
        return user;
    }

    public ToohakGame(Controller application) {
        this.application = application;
    }

    @Override
    public void processNextRound(IGame game) {
        if (game instanceof IHostGame) {
            application.processNextRoundHost(((HostGame) game).getQuestions().get(((HostGame) game).getCurrentRandom()));
        } else {
            application.processNextRoundClient();
        }
    }

    @Override
    public void nextRound() {
        game.nextRound();
    }

    @Override
    public String registerPlayer(String username, String password, String email) {
        RegisterAction action = new RegisterAction();
        return action.register(new Register(email, username, password));
    }

    @Override
    public String login(String useremail, String password) {
        LoginAction action = new LoginAction();
        String result = action.login(new Login(useremail, password));
        Gson gson = new Gson();
        try {
            LoginResponse response = gson.fromJson(result, LoginResponse.class);
            if (response.getAdmin() != null) {
                this.user = response.getAdmin();
                return "Login successful - admin";
            } else
                this.user = response.getUser();
            return "Login successful";
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
        }
        return result;
    }

    @Override
    public String clearLobbies() {
        ClearLobbiesAction action = new ClearLobbiesAction();
        return action.clearLobbies(new ClearLobbies());
    }

    @Override
    public void chooseHostOrClient(IGame game) {
        if (game instanceof IHostGame) {
            ((IHostGame) game).setMessageGenerator(new ServerMessageGenerator());
        }
        this.game = game;
        game.setIToohakGame(this);
    }

    @Override
    public List<Lobby> refreshLobbies() {
        return ((IClientGame) game).refreshLobbies();
    }

    @Override
    public String createLobby(Lobby lobby) {
        return ((IHostGame) game).createLobby(lobby);
    }

    @Override
    public void joinLobby(Lobby lobby) {
        ((IClientGame) game).setMessageGenerator(new ClientMessageGenerator());
        ((IClientGame) game).joinLobby(lobby);
    }

    @Override
    public void answerQuestion(MultipleChoice answer) {
        ((IClientGame) game).sendAnswer(answer);
    }

    @Override
    public void processAnswerReply(boolean correct) {
        if (correct) {
            application.processAnswerCorrect();
        } else {
            application.processAnswerWrong();
        }
    }

    @Override
    public void startGame() {
        ((IHostGame) game).startGame();
    }

    @Override
    public void processEndGame(IGame game) {
        if (game instanceof IClientGame)
            application.processEndGameClient();
        else {
            application.processEndGameHost();
        }
    }

    @Override
    public void processStartGame() {
        application.processStartGameClient();
    }

    public void addToLobbyList(User user) {
        application.addToLobbyList(user);
    }

}