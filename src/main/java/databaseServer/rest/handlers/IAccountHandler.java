package databaseServer.rest.handlers;


import databaseServer.rest.response.*;
import shared.request.Login;
import shared.request.Register;

public interface IAccountHandler {
    Reply login(Login data);

    Reply register(Register data);

}
