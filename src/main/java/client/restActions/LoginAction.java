package client.restActions;

import shared.request.Login;

public class LoginAction extends AccountAction<Login> {

    String query = "http://localhost:8090/account/login";

    public String login(Login data) {
    return    super.baseMethod(data, query);
    }
}
