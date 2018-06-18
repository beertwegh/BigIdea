package testhandlersrest;

import com.google.gson.Gson;
import databaseserver.datacontext.QuestionDataContext;
import databaseserver.repositories.QuestionRepository;
import databaseserver.rest.handlers.QuestionHandler;
import databaseserver.rest.response.QuestionResponse;
import databaseserver.rest.response.Reply;
import databaseserver.rest.response.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class QuestionHandlerTest extends DbCleaner {

    private QuestionHandler handler;
    private Gson gson = new Gson();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        handler = new QuestionHandler(new QuestionRepository(new QuestionDataContext(testConnString)));
    }

    @Test
    public void testGetAllQuestions() {
        //condition : Table contains 3 questions, as test db does at this moment
        Reply reply = handler.getAllQuestions();
        Assert.assertEquals(Status.OK, reply.getStatus());
        QuestionResponse response = gson.fromJson(reply.getMessage(), QuestionResponse.class);
        Assert.assertEquals(3, response.getQuestions().size());
    }
}
