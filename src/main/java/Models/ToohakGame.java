package Models;

import interfaces.IGame;
import interfaces.IToohakGame;

public class ToohakGame implements IToohakGame{

    private boolean host;
    private Lobby lobbies;
    private IGame game;

    /**
     * @param username
     * @param password
     * @param email
     */
    public void registerPlayer(String username, String password, String email) {
        // TODO - implement Models.ToohakGame.registerPlayer
        throw new UnsupportedOperationException();
    }

    /**
     * @param useremail
     * @param password
     */
    public boolean login(String useremail, String password) {
        // TODO - implement Models.ToohakGame.login
        throw new UnsupportedOperationException();
    }

    /**
     * @param host
     */
    public void chooseHostOrClient(boolean host) {
        if (host) {
            game = new HostGame();
        } else {
            game = new ClientGame();
        }
    }

    /**
     * @param lobby
     */
    public void selectLobby(Lobby lobby) {

    }

    public void refreshLobbies() {
        // TODO - implement Models.ToohakGame.refreshLobbies
        throw new UnsupportedOperationException();
    }

    public void createLobby() {
        // TODO - implement Models.ToohakGame.createLobby
        throw new UnsupportedOperationException();
    }

    public void answerQuestion(Question question) {

    }

    /**
     * @param answer
     */
    public void answerQuestion(Answer answer) {
        // TODO - implement Models.ToohakGame.answerQuestion
        throw new UnsupportedOperationException();
    }

}