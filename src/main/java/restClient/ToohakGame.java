package restClient;

import Models.Answer;
import Models.Lobby;
import Models.Question;
import Models.User;
import restClient.host.*;
import restClient.player.ClientGame;
import restClient.player.IClientGame;
import restClient.restActions.LoginAction;
import restClient.restActions.RegisterAction;
import interfaces.IGame;
import interfaces.IToohakGame;
import shared.restrequest.Login;
import shared.restrequest.Register;
import userinterface.Controller;
import userinterface.ToohakApplication;

import java.util.List;

public class ToohakGame implements IToohakGame {

    private IGame game;
    private Controller application;
    private User user;

    public ToohakGame(Controller application) {
        this.application = application;
    }

    /**
     * @param username
     * @param password
     * @param email
     */
    public String registerPlayer(String username, String password, String email) {
        RegisterAction action = new RegisterAction();
        return action.register(new Register(email, username, password));
    }

    /**
     * @param useremail
     * @param password
     */
    public String login(String useremail, String password) {
        LoginAction action = new LoginAction();
        return action.login(new Login(useremail, password));
    }

    /**
     * @param host
     */
    public void chooseHostOrClient(boolean host) {
        if (host) {
            game = new HostGame(this);
        } else {
            game = new ClientGame();
        }
    }

    /**
     * @param lobby
     */
    public void selectLobby(Lobby lobby) {
//todo
    }

    public List<Lobby> refreshLobbies() {
        return ((IClientGame) game).refreshLobbies();
    }

    @Override
    public boolean createLobby(Lobby lobby) {
        return ((HostGame) game).createLobby(lobby);
    }

    public void answerQuestion(Question question) {
//todo
    }

    @Override
    public void startGame() {
        ((HostGame) game).startGame();
    }

    public void addToLobbyList(User user) {
        application.addToLobbyList(user);
    }

}