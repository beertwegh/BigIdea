package stubs;

import interfaces.IGame;
import interfaces.IToohakGame;
import models.Lobby;
import models.User;
import shared.MultipleChoice;

import java.util.List;

public class ToohakGameStub implements IToohakGame {
    @Override
    public void nextRound() {

    }

    @Override
    public String registerPlayer(String username, String password, String email) {
        return null;
    }

    @Override
    public String login(String useremail, String password) {
        return null;
    }

    @Override
    public String clearLobbies() {
        return null;
    }

    @Override
    public void chooseHostOrClient(boolean host) {

    }

    @Override
    public List<Lobby> refreshLobbies() {
        return null;
    }

    @Override
    public String createLobby(Lobby lobby) {
        return null;
    }

    @Override
    public void joinLobby(Lobby lobby) {

    }

    @Override
    public void answerQuestion(MultipleChoice multipleChoice) {

    }

    @Override
    public void processAnswerReply(boolean correct) {
        AppFlowStack.addStack(String.valueOf(correct));
    }

    @Override
    public void startGame() {

    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public void processNextRound(IGame game) {

    }

    @Override
    public void processEndGame(IGame game) {

    }


    @Override
    public void processStartGame() {

    }
}
