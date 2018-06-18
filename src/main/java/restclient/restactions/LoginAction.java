package restclient.restactions;

import shared.restrequest.Login;

public class LoginAction implements BaseAction<Login> {

    String query = "http://rest.basvdeertwegh.nl/account/login";

    public String login(Login data) {
        return baseMethod(data, query);
    }
}