package databaseserver.rest.handlers;

import com.google.gson.Gson;
import databaseserver.repositories.ILobbyRepository;
import databaseserver.rest.response.GetLobbiesResponse;
import databaseserver.rest.response.Reply;
import databaseserver.rest.response.Status;
import databaseserver.speicifiables.LobbySpecifiable;
import models.Lobby;

public class LobbyHandler implements ILobbyHandler

{
    private ILobbyRepository repository;

    public LobbyHandler(ILobbyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Reply getLobbies() {
        GetLobbiesResponse response = new GetLobbiesResponse();
        response.setLobbies(repository.findAll());
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(response);
        return new Reply(Status.OK, jsonResponse);
    }

    @Override
    public Reply createLobby(Lobby lobby) {
        repository.save(lobby);
        return new Reply(Status.OK, "Lobby has been added");
    }

    @Override
    public Reply chooseLobby(Lobby lobby) {
        if (repository.findOne(LobbySpecifiable.getByIp(lobby.getIp())) != null) {
            return new Reply(Status.OK, "Connect to lobby");
        }
        return new Reply(Status.NOTFOUND, "Lobby doesn't exist");
    }

    @Override
    public Reply clearLobbies() {
        repository.clearLobbies();
        return new Reply(Status.OK, "Lobbies have been cleared");
    }

}
