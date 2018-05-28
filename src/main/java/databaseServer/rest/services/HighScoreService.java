package databaseServer.rest.services;

import databaseServer.rest.handlers.IHighScoreHandler;
import databaseServer.rest.request.SetHighScore;
import databaseServer.rest.response.Reply;
import com.google.gson.Gson;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/highscore")
public class HighScoreService {
    private static IHighScoreHandler handler;

    public static void setHandler(IHighScoreHandler highscorehandler) {
        handler = highscorehandler;
    }

    @POST
    @Path("/set")
    @Consumes("application/json")
    public Response setHighScore(String data) {
        Gson json = new Gson();
        SetHighScore setHighScore = json.fromJson(data, SetHighScore.class);
        Reply reply = handler.setHighScore(setHighScore);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }
}
