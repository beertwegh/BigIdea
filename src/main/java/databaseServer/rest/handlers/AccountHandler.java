package databaseServer.rest.handlers;

import databaseServer.rest.response.*;
import databaseServer.repositories.IUserRepository;
import databaseServer.speicifiables.UserSpecifiable;
import Models.User;
import shared.restrequest.Login;
import shared.restrequest.Register;

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
        return new Reply(Status.Ok, "Login successful");
    }

    @Override
    public Reply register(Register data) {
        User user = new User(data.getUsername(), data.getEmail(), data.getPassword());
        repository.save(user);
        return new Reply(Status.Ok, "Account has been made");
    }


}
