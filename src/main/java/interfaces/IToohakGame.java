package interfaces;


import models.Lobby;
import models.User;
import shared.MultipleChoice;

import java.util.List;

public interface IToohakGame {
    void nextRound();

    String registerPlayer(String username, String password, String email);

    String login(String useremail, String password);

    String clearLobbies();


    void chooseHostOrClient(IGame game);

    List<Lobby> refreshLobbies();

    String createLobby(Lobby lobby);

    void joinLobby(Lobby lobby);

    void answerQuestion(MultipleChoice multipleChoice);

    void processAnswerReply(boolean correct);

    void startGame();

    User getUser();

    void processNextRound(IGame game);


    void processEndGame(IGame game);

    void processStartGame();
}