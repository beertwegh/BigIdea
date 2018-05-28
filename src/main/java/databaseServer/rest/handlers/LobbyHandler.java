package databaseServer.rest.handlers;

import Models.Lobby;
import com.google.gson.Gson;
import databaseServer.repositories.ILobbyRepository;
import databaseServer.rest.response.GetLobbiesResponse;
import databaseServer.rest.response.Reply;
import databaseServer.rest.response.Status;
import databaseServer.speicifiables.LobbySpecifiable;

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
        return new Reply(Status.Ok, jsonResponse);
    }

    @Override
    public Reply createLobby(Lobby lobby) {
        repository.save(lobby);
        return new Reply(Status.Ok, "Lobby has been added");
    }

    @Override
    public Reply chooseLobby(Lobby lobby) {
        if (repository.findOne(LobbySpecifiable.getByIp(lobby.getIp())) != null) {
            return new Reply(Status.Ok, "Connect to lobby");
        }
        return new Reply(Status.NotFound, "Lobby doesn't exist");
    }
}
