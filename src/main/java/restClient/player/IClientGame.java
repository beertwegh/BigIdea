package restClient.player;

import Models.Lobby;
import interfaces.IGame;
import shared.MultipleChoice;

import java.util.List;

public interface IClientGame extends IGame {

    void sendAnswer(MultipleChoice answer);

    List<Lobby> refreshLobbies();

    void joinLobby(Lobby lobby);

}
