package DatabaseServer.rest.services;

import DatabaseServer.rest.handlers.IQuestionHandler;
import DatabaseServer.rest.response.Reply;
import com.google.gson.Gson;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/question")
public class QuestionService {

    private static IQuestionHandler handler;

    public static void setHandler(IQuestionHandler questionhandler) {
        handler = questionhandler;
    }

    @POST
    @Path("/set")
    @Consumes("application/json")
    public Response setHighScore(String data) {
        Gson json = new Gson();
        Reply reply = handler.getAllQuestions();
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }
}
