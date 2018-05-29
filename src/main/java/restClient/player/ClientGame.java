package restClient.player;

import Models.Answer;
import Models.Lobby;
import Models.Question;
import databaseServer.datacontext.LobbyDataContext;
import databaseServer.repositories.ILobbyRepository;
import databaseServer.repositories.LobbyRepository;

import java.util.List;

public class ClientGame implements IClientGame {

    private Question question;


    @Override
    public void sendAnswer(Answer answer) {

    }

    @Override
    public List<Lobby> refreshLobbies() {
        ILobbyRepository lobbyRepository = new LobbyRepository(new LobbyDataContext());
        return lobbyRepository.findAll();
    }

    @Override
    public void nextRound() {

    }
}