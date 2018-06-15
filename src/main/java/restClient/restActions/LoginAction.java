package restClient.restActions;

import shared.restrequest.Login;

public class LoginAction extends BaseAction<Login> {

    String query = "http://rest.basvdeertwegh.nl/account/login";

    public String login(Login data) {
        return super.baseMethod(data, query);
    }
}