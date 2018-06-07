package restClient.player;

import Models.Lobby;
import Models.MultipleChoice;
import Models.Question;
import databaseServer.datacontext.LobbyDataContext;
import databaseServer.repositories.ILobbyRepository;
import databaseServer.repositories.LobbyRepository;
import interfaces.IToohakGame;
import restClient.player.websocket.ClientWebSocket;
import restClient.restActions.GetLobbies;

import java.util.List;

public class ClientGame implements IClientGame {

    private Question question;

    private IToohakGame game;

    private ClientWebSocket socket;

    public ClientGame(IToohakGame game) {
        this.game = game;
    }

    @Override
    public void sendAnswer(MultipleChoice answer) {

    }


    @Override
    public List<Lobby> refreshLobbies() {
        GetLobbies getlobbies = new GetLobbies();
        return getlobbies.getLobbies();
    }

    @Override
    public void joinLobby(Lobby lobby) {
        socket = new ClientWebSocket();
        socket.start(lobby.getIp());

}

    @Override
    public void nextRound() {

    }
}