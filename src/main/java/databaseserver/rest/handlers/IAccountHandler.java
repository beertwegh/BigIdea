package databaseserver.rest.handlers;


import databaseserver.rest.response.Reply;
import shared.restrequest.Login;
import shared.restrequest.Register;

public interface IAccountHandler {
    Reply login(Login data);

    Reply register(Register data);

}
