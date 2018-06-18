package databaseserver.repositories;

import databaseserver.datacontext.ILobbyDataContext;
import databaseserver.speicifiables.Specifiable;
import models.Lobby;

import java.util.List;

public class LobbyRepository implements ILobbyRepository {

    private ILobbyDataContext dataContext;

    public LobbyRepository(ILobbyDataContext dataContext) {
        this.dataContext = dataContext;
    }

    @Override
    public Lobby findOne(Specifiable specifiable) {
        return dataContext.findOne(specifiable);
    }

    @Override
    public void save(Lobby item) {
        dataContext.save(item);
    }

    @Override
    public List<Lobby> findAll() {
        return dataContext.findAll();
    }

    @Override
    public void clearLobbies() {
        dataContext.clearLobbies();
    }
}
