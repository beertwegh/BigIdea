package databaseServer.rest.response;

import Models.Lobby;
import Models.Question;

import java.util.List;

public class GetLobbiesResponse {


    private List<Lobby> lobbies;


    public void setLobbies(List<Lobby> lobbies) {
        this.lobbies = lobbies;
    }

    public List<Lobby> getLobbies() {
        return lobbies;
    }
}
