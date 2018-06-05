package restClient.player;

import Models.Lobby;
import Models.MultipleChoice;
import Models.Question;
import databaseServer.datacontext.LobbyDataContext;
import databaseServer.repositories.ILobbyRepository;
import databaseServer.repositories.LobbyRepository;
import interfaces.IToohakGame;
import restClient.restActions.GetLobbies;

import java.util.List;

public class ClientGame implements IClientGame {

    private Question question;

    private IToohakGame game;

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
    public void nextRound() {

    }
}