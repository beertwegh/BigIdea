package databaseServer.rest.handlers;

import databaseServer.rest.response.Reply;
import shared.restrequest.SetHighScore;

public interface IHighScoreHandler {
    Reply setHighScore(SetHighScore data);

}
