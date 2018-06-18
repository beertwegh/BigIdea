package databaseserver.rest.handlers;

import databaseserver.rest.response.Reply;
import shared.restrequest.SetHighScore;

public interface IHighScoreHandler {
    Reply setHighScore(SetHighScore data);

}
