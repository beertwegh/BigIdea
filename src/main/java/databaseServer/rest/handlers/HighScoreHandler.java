package databaseServer.rest.handlers;

import databaseServer.repositories.IHighScoreRepository;
import databaseServer.rest.request.SetHighScore;
import databaseServer.rest.response.Reply;
import databaseServer.rest.response.Status;

public class HighScoreHandler implements IHighScoreHandler {
    private IHighScoreRepository repository;

    public HighScoreHandler(IHighScoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Reply setHighScore(SetHighScore data) {
        repository.updateScore(data.getId(), data.getScore());
        return new Reply(Status.Ok, "Score has been updated");
    }
}
