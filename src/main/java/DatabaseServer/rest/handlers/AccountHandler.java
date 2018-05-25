package DatabaseServer.rest.handlers;

import DatabaseServer.rest.response.*;
import DatabaseServer.Repositories.IUserRepository;
import DatabaseServer.rest.request.*;
import DatabaseServer.Specifiables.UserSpecifiable;
import Models.User;

public class AccountHandler implements IAccountHandler {
    private IUserRepository repository;

    public AccountHandler(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Reply login(Login data) {
        User user = repository.findOne(UserSpecifiable.getByEmail(data.getUseremail()));
        if (user == null) {
            user = repository.findOne(UserSpecifiable.getbyUsername(data.getUseremail()));
            if (user == null)
                return new Reply(Status.NotFound, "Player doesn't exist");
        } else if (!(user.getPassword()).equals(data.getPassword())) {
            return new Reply(Status.NoAccess, "Your login credentials were incorrect");
        }
        return new Reply(Status.Ok, "succeeded");
    }
}
