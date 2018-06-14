package testhandlersrest;

import databaseServer.datacontext.HighScoreDataContext;
import databaseServer.repositories.HighScoreRepository;
import databaseServer.rest.handlers.HighScoreHandler;
import databaseServer.rest.response.Reply;
import databaseServer.rest.response.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import shared.restrequest.SetHighScore;

public class HighScoreHandlerTest extends DbCleaner {
    private HighScoreHandler handler;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        handler = new HighScoreHandler(new HighScoreRepository(new HighScoreDataContext(testConnString)));
        emptyTable("HighScore");
    }

    @Test
    public void TestSetHighScore() {
        SetHighScore setHighScore = new SetHighScore(1, 10);
        Reply reply = handler.setHighScore(setHighScore);
        Assert.assertEquals(Status.OK, reply.getStatus());
    }
}
