package restclient.host;

import interfaces.IGame;
import models.Lobby;
import models.User;
import restclient.host.websocket.IServerMessageGenerator;
import shared.MultipleChoice;

public interface IHostGame extends IGame {
    void setMessageGenerator(IServerMessageGenerator messageGenerator);

    String createLobby(Lobby lobby);

    void startGame();

    void gameEnded();

    void processPlayerJoined(User user);

    void processAnswerQuestion(User user, MultipleChoice answer);
}
