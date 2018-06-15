package databaseServer.speicifiables;


import models.User;

public class UserSpecifiable implements Specifiable<User> {

    private String specifiable;
    private String parameter;

    @Override
    public String getSpecifiable() {
        return specifiable;
    }

    @Override
    public String getParameter() {
        return parameter;
    }

    public UserSpecifiable(String specifiable, String parameter) {
        this.specifiable = specifiable;
        this.parameter = parameter;
    }

    public static UserSpecifiable getByEmail(String email) {
        return new UserSpecifiable("email", email);
    }

    public static UserSpecifiable getbyUsername(String username) {
        return new UserSpecifiable("username", username);
    }
}
