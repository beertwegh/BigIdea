package databaseServer.rest.handlers;

import databaseServer.rest.request.SetHighScore;
import databaseServer.rest.response.Reply;

public interface IHighScoreHandler {
    Reply setHighScore(SetHighScore data);

}
