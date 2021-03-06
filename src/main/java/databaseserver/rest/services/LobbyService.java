package databaseserver.rest.services;

import com.google.gson.Gson;
import databaseserver.rest.handlers.ILobbyHandler;
import databaseserver.rest.response.Reply;
import models.Lobby;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/lobby")
public class LobbyService {
    private static ILobbyHandler handler;

    public static void setHandler(ILobbyHandler handler) {
        LobbyService.handler = handler;
    }

    @GET
    @Path("/getAll")
    public Response getAllLobbies() {
        Reply reply = handler.getLobbies();
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @POST
    @Path("/create")
    @Consumes("application/json")
    public Response createLobby(String data) {
        Gson gson = new Gson();
        Lobby lobby = gson.fromJson(data, Lobby.class);
        Reply reply = handler.createLobby(lobby);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @POST
    @Path("/choose")
    @Consumes("application/json")
    public Response chooseLobby(String data) {
        Gson gson = new Gson();
        Lobby lobby = gson.fromJson(data, Lobby.class);
        Reply reply = handler.chooseLobby(lobby);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @POST
    @Path("/clear")
    @Consumes("application/json")
    public Response clearLobbies(String data) {
        Reply reply = handler.clearLobbies();
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }
}

