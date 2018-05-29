package interfaces;


import Models.*;

import java.util.List;

public interface IToohakGame {

    /**
     * @param username
     * @param password
     * @param email
     */
    String registerPlayer(String username, String password, String email);

    /**
     * @param useremail
     * @param password
     */
    String login(String useremail, String password);

    /**
     * @param host
     */
    void chooseHostOrClient(boolean host);

    /**
     * @param lobby
     */
    void selectLobby(Lobby lobby);

    List<Lobby> refreshLobbies();


    boolean createLobby(Lobby lobby);

    /**
     * @param question
     */
    void answerQuestion(Question question);

    void startGame();

}