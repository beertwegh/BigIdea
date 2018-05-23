package DatabaseServer.Repositories;

import DatabaseServer.DataContext.CredentialsDataContext;
import DatabaseServer.Specifiables.Specifiable;

import java.util.List;

public class UserRepository implements IRepository {

    public UserRepository(IRepository dataContext) {
        this.dataContext = dataContext;
    }

    private IRepository dataContext;

    public Object findOne(int id) {
        return null;
    }

    public Object findOne(Specifiable specifiable) {
        return null;
    }

    public void save(Object item) {

    }

    public List<Object> findAll() {
        return null;
    }
}
