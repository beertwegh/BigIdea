package restClient.restActions;

import Models.Lobby;

public class SaveLobby extends BaseAction<Lobby> {
    String query = "http://localhost:8090/lobby/create";

    public String saveLobby(Lobby lobby) {
        return super.baseMethod(lobby, query);
    }

}
