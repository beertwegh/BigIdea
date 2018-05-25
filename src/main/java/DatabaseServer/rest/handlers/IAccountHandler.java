package DatabaseServer.rest.handlers;


import DatabaseServer.rest.response.*;
import DatabaseServer.rest.request.*;

public interface IAccountHandler {
    Reply login(Login data);

    Reply register(Register data);

}
