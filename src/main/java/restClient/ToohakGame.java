package restClient;

import Models.Lobby;
import Models.User;
import com.google.gson.Gson;
import interfaces.IGame;
import interfaces.IToohakGame;
import restClient.host.HostGame;
import restClient.host.IHostGame;
import restClient.player.ClientGame;
import restClient.player.IClientGame;
import restClient.restActions.LoginAction;
import restClient.restActions.RegisterAction;
import shared.Logging.Logger;
import shared.MultipleChoice;
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
            this.user = gson.fromJson(result, User.class);
            return "Login successful";
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
        }
        return result;
    }

    @Override
    public void chooseHostOrClient(boolean host) {
        if (host) {
            game = new HostGame(this);
        } else {
            game = new ClientGame(this);
        }
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
    public void processStartGame() {
        application.processStartGameClient();
    }

    public void addToLobbyList(User user) {
        application.addToLobbyList(user);
    }

}