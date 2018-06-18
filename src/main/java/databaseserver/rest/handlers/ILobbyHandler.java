package databaseserver.rest.handlers;

import databaseserver.rest.response.Reply;
import models.Lobby;

public interface ILobbyHandler {

    Reply getLobbies();

    Reply createLobby(Lobby lobby);

    Reply chooseLobby(Lobby lobby);

    Reply clearLobbies();
}
