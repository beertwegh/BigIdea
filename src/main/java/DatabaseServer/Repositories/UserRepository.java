package DatabaseServer.Repositories;

import DatabaseServer.DataContext.CredentialsDataContext;
import DatabaseServer.DataContext.IDataContext;
import DatabaseServer.Specifiables.Specifiable;
import Models.User;

import java.util.List;

public class UserRepository implements IUserRepository {

    private IDataContext dataContext;

    public UserRepository(IDataContext dataContext) {
        this.dataContext = dataContext;
    }

    public User findOne(int id) {
        return null;
    }

    public User findOne(Specifiable specifiable) {
        return null;
    }

    @Override
    public void save(User user) {
        dataContext.save(user);
    }

    public List<User> findAll() {
        return dataContext.findAll();
    }
}
