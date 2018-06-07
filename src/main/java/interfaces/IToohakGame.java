package interfaces;


import Models.*;
import shared.MultipleChoice;

import java.util.List;

public interface IToohakGame {
    String registerPlayer(String username, String password, String email);

    String login(String useremail, String password);

    void chooseHostOrClient(boolean host);

    List<Lobby> refreshLobbies();

    String createLobby(Lobby lobby);

    void joinLobby(Lobby lobby);

    void answerQuestion(MultipleChoice multipleChoice);

    void startGame();

    User getUser();
}