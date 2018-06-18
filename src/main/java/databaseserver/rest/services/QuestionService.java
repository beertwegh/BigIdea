package databaseserver.rest.services;

import databaseserver.rest.handlers.IQuestionHandler;
import databaseserver.rest.response.Reply;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
