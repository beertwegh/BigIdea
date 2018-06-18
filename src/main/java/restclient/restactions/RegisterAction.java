package restclient.restactions;

import shared.restrequest.Register;

public class RegisterAction implements BaseAction<Register> {

    String query = "http://rest.basvdeertwegh.nl/account/register";

    public String register(Register data) {
        return baseMethod(data, query);
    }
}
