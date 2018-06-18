package restClient.restactions;

import shared.restrequest.Register;

public class RegisterAction extends BaseAction<Register> {

    String query = "http://rest.basvdeertwegh.nl/account/register";

    public String register(Register data) {
        return super.baseMethod(data, query);
    }
}
