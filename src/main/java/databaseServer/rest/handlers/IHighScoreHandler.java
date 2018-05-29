package databaseServer.rest.handlers;

import shared.restrequest.SetHighScore;
import databaseServer.rest.response.Reply;

public interface IHighScoreHandler {
    Reply setHighScore(SetHighScore data);

}
