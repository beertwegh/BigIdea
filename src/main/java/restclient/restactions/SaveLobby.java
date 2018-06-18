package restclient.restactions;

import models.Lobby;

public class SaveLobby implements BaseAction<Lobby> {
    String query = "http://rest.basvdeertwegh.nl/lobby/create";

    public String saveLobby(Lobby lobby) {
        return baseMethod(lobby, query);
    }

}
