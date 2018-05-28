package databaseServer.rest.services;

import databaseServer.rest.handlers.IQuestionHandler;
import databaseServer.rest.response.Reply;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/question")
public class QuestionService {

    private static IQuestionHandler handler;

    public static void setHandler(IQuestionHandler questionhandler) {
        handler = questionhandler;
    }

    @GET
    @Path("/getAll")
    public Response getAll() {
        Reply reply = handler.getAllQuestions();
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }
}
