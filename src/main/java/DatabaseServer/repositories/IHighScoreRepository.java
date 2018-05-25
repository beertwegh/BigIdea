package DatabaseServer.repositories;

import Models.User;

public interface IHighScoreRepository {
    public void updateScore(int userId, int score);
}
