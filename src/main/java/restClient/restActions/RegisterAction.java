package restClient.restActions;

import shared.restrequest.Register;

public class RegisterAction extends BaseAction<Register> {

    String query = "http://localhost:8090/account/register";

    public String register(Register data) {
        return super.baseMethod(data, query);
    }
}
