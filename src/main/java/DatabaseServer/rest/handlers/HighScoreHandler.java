package DatabaseServer.rest.handlers;

import DatabaseServer.repositories.IHighScoreRepository;
import DatabaseServer.rest.request.SetHighScore;
import DatabaseServer.rest.response.Reply;
import DatabaseServer.rest.response.Status;

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
