package DatabaseServer.rest.services;

import DatabaseServer.rest.response.*;
import DatabaseServer.rest.request.*;
import DatabaseServer.rest.handlers.IAccountHandler;
import com.google.gson.Gson;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/account")
public class AccountService {

    private static IAccountHandler handler;

    public static void setHandler(IAccountHandler accountHandler) {
        accountHandler = accountHandler;
    }

    @POST @Consumes("application/json")
    @Path("/login")
    public Response login(String data) {
        Gson json = new Gson();
        Login login = json.fromJson(data, Login.class);
        Reply reply = handler.login(login);

        return Response.status(200).entity(null).build();
    }

}
