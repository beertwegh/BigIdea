package restclient.player;

import interfaces.IGame;
import models.Lobby;
import restclient.player.websocket.IClientMessageGenerator;
import shared.MultipleChoice;

import java.util.List;

public interface IClientGame extends IGame {

    void setMessageGenerator(IClientMessageGenerator messageGenerator);

    void sendAnswer(MultipleChoice answer);

    List<Lobby> refreshLobbies();

    void joinLobby(Lobby lobby);


    void processStartGame();

    void processAnswerReply(boolean correct);

    void processEndGame();
}
