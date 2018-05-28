package databaseServer.repositories;


import databaseServer.datacontext.IHighScoreDataContext;

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
