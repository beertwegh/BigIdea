package client.restActions;

import shared.request.Register;

public class RegisterAction extends AccountAction<Register> {

    String query = "http://localhost:8090/account/register";

    public String register(Register data) {
       return super.baseMethod(data, query);
    }
}
