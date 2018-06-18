package databaseserver.datacontext;

import models.Lobby;

public interface ILobbyDataContext extends IDataContext<Lobby> {

    void clearLobbies();

}
