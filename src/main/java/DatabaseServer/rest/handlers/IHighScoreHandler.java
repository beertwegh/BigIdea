package DatabaseServer.rest.handlers;

import DatabaseServer.rest.request.SetHighScore;
import DatabaseServer.rest.response.Reply;

public interface IHighScoreHandler {
    Reply setHighScore(SetHighScore data);

}
