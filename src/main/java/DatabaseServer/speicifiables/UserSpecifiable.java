package DatabaseServer.speicifiables;


import Models.User;

public class UserSpecifiable implements Specifiable<User> {

    private String specifiable;
    private String parameter;

    public String getSpecifiable() {
        return specifiable;
    }

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
