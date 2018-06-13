package databaseServer.rest.services;

import com.google.gson.Gson;
import databaseServer.rest.handlers.IAccountHandler;
import databaseServer.rest.response.Reply;
import shared.restrequest.Login;
import shared.restrequest.Register;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/account")
public class AccountService {

    private static IAccountHandler handler;

    public static void setHandler(IAccountHandler accountHandler) {
        handler = accountHandler;
    }

    @POST
    @Consumes("application/json")
    @Path("/login")
    public Response login(String data) {
        Gson json = new Gson();
        Login login = json.fromJson(data, Login.class);
        Reply reply = handler.login(login);
        return Response.status(reply.getStatus().getCode())
                .entity(reply.getMessage()).build();
    }

    @POST
    @Consumes("application/json")
    @Path("/register")
    public Response register(String data) {
        Gson json = new Gson();
        Register register = json.fromJson(data, Register.class);
        Reply reply = handler.register(register);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

}
