package databaseserver.repositories;

public interface IHighScoreRepository {
    public void updateScore(int userId, int score);
}
