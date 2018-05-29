package databaseServer.rest.handlers;


import databaseServer.rest.response.*;
import shared.restrequest.Login;
import shared.restrequest.Register;

public interface IAccountHandler {
    Reply login(Login data);

    Reply register(Register data);

}
