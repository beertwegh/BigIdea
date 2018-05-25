package DatabaseServer.repositories;


import DatabaseServer.datacontext.IHighScoreDataContext;
import Models.User;

public class HighScoreRepository implements IHighScoreRepository {
    private IHighScoreDataContext dataContext;

    public HighScoreRepository(IHighScoreDataContext dataContext) {
        this.dataContext = dataContext;
    }

    @Override
    public void updateScore(int userId, int score) {
        dataContext.updateScore(userId, score);
    }
}
