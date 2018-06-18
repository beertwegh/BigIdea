package databaseServer.repositories;

import databaseServer.datacontext.ICredentialsDataContext;
import databaseServer.speicifiables.Specifiable;
import models.User;

import java.util.List;

public class UserRepository implements IUserRepository {

    private ICredentialsDataContext dataContext;

    public UserRepository(ICredentialsDataContext dataContext) {
        this.dataContext = dataContext;
    }


    public User findOne(Specifiable specifiable) {
        return dataContext.findOne(specifiable);
    }

    @Override
    public void save(User user) {
        dataContext.save(user);
    }

    public List<User> findAll() {
        return dataContext.findAll();
    }
}
