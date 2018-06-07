package restClient.player;

import Models.Answer;
import Models.Lobby;
import Models.MultipleChoice;
import interfaces.IGame;

import java.util.List;

public interface IClientGame extends IGame {

    void sendAnswer(MultipleChoice answer);

    List<Lobby> refreshLobbies();

    void joinLobby(Lobby lobby);

}
