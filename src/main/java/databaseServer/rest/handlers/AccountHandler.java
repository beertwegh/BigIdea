package databaseServer.rest.handlers;

import com.google.gson.Gson;
import databaseServer.repositories.IUserRepository;
import databaseServer.rest.response.LoginResponse;
import databaseServer.rest.response.Reply;
import databaseServer.rest.response.Status;
import databaseServer.speicifiables.UserSpecifiable;
import models.Admin;
import models.User;
import shared.Logging.Logger;
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
                return new Reply(Status.NOTFOUND, "Player doesn't exist");
        }
        if (!(user.getPassword()).equals(data.getPassword())) {
            return new Reply(Status.NOACCESS, "Your login credentials were incorrect");
        }
        LoginResponse response;
        if (user instanceof Admin) {
            response = new LoginResponse((Admin) user);
        } else {
            response = new LoginResponse(user);
        }
        Gson gson = new Gson();
        String json = gson.toJson(response);
        return new Reply(Status.OK, json);
    }

    @Override
    public Reply register(Register data) {
        User user = new User(data.getUsername(), data.getEmail(), data.getPassword());
        try {
            repository.save(user);
            return new Reply(Status.OK, "Account has been made");

        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            return new Reply(Status.ERROR, "something went wrong");
        }
    }


}
