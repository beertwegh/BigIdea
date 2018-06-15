package databaseServer.rest.handlers;

import models.Lobby;
import databaseServer.rest.response.Reply;

public interface ILobbyHandler {

    Reply getLobbies();

    Reply createLobby(Lobby lobby);

    Reply chooseLobby(Lobby lobby);

    Reply clearLobbies();
}
