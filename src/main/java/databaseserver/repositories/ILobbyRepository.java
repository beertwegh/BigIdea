package databaseserver.repositories;

import models.Lobby;

public interface ILobbyRepository extends IRepository<Lobby> {
    void clearLobbies();
}
