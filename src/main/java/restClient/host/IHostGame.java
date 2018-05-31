package restClient.host;

import Models.Lobby;
import Models.User;
import interfaces.IGame;

public interface IHostGame extends IGame {
    String createLobby(Lobby lobby);

    void startGame();

    void gameEnded();

    void processPlayerJoined(User user);
}
