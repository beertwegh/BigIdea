package databaseServer.rest.handlers;

import databaseServer.rest.response.Reply;
import models.Lobby;

public interface ILobbyHandler {

    Reply getLobbies();

    Reply createLobby(Lobby lobby);

    Reply chooseLobby(Lobby lobby);

    Reply clearLobbies();
}
