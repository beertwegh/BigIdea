package restClient;

import Models.Lobby;
import Models.Question;
import Models.User;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import restClient.host.*;
import restClient.player.ClientGame;
import restClient.player.IClientGame;
import restClient.restActions.LoginAction;
import restClient.restActions.RegisterAction;
import interfaces.IGame;
import interfaces.IToohakGame;
import shared.Logging.Logger;
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
        String result = action.login(new Login(useremail, password));
        Gson gson = new Gson();
        try {
            this.user = gson.fromJson(result, User.class);
            return "Login successful";
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
        }
        return result;
    }

    /**
     * @param host
     */
    public void chooseHostOrClient(boolean host) {
        if (host) {
            game = new HostGame(this);
        } else {
            game = new ClientGame(this);
        }
    }

    public List<Lobby> refreshLobbies() {
        return ((IClientGame) game).refreshLobbies();
    }

    @Override
    public String createLobby(Lobby lobby) {
        return ((IHostGame) game).createLobby(lobby);
    }

    @Override
    public void joinLobby(Lobby lobby) {
        ((IClientGame) game).joinLobby(lobby);
    }

    public void answerQuestion(Question question) {
//todo
    }

    @Override
    public void startGame() {
        ((IHostGame) game).startGame();
    }

    public void addToLobbyList(User user) {
        application.addToLobbyList(user);
    }

}