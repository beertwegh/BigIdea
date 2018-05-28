package databaseServer.rest.handlers;


import databaseServer.rest.response.*;
import databaseServer.rest.request.*;

public interface IAccountHandler {
    Reply login(Login data);

    Reply register(Register data);

}
