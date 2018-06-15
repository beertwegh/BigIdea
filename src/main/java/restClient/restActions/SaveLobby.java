package restClient.restActions;

import Models.Lobby;

public class SaveLobby extends BaseAction<Lobby> {
    String query = "http://rest.basvdeertwegh.nl/lobby/create";

    public String saveLobby(Lobby lobby) {
        return super.baseMethod(lobby, query);
    }

}
