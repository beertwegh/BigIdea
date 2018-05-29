package restClient.player;

import Models.Answer;
import Models.Lobby;
import interfaces.IGame;

import java.util.List;

public interface IClientGame extends IGame{

    void sendAnswer(Answer answer);

    List<Lobby> refreshLobbies();


}
