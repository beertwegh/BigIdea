package databaseServer.rest.response;

import models.Admin;
import models.User;

public class LoginResponse {
    private Admin admin;
    private User user;

    public LoginResponse(Admin admin) {
        this.admin = admin;
    }

    public LoginResponse(User user) {
        this.user = user;
    }

    public Admin getAdmin() {
        return admin;
    }

    public User getUser() {
        return user;
    }
}
