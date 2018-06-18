package databaseserver.rest.handlers;

import databaseserver.repositories.IHighScoreRepository;
import databaseserver.rest.response.Reply;
import databaseserver.rest.response.Status;
import shared.restrequest.SetHighScore;

public class HighScoreHandler implements IHighScoreHandler {
    private IHighScoreRepository repository;

    public HighScoreHandler(IHighScoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Reply setHighScore(SetHighScore data) {
        repository.updateScore(data.getId(), data.getScore());
        return new Reply(Status.OK, "Score has been updated");
    }
}