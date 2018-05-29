package databaseServer.rest.handlers;

import shared.request.SetHighScore;
import databaseServer.rest.response.Reply;

public interface IHighScoreHandler {
    Reply setHighScore(SetHighScore data);

}
