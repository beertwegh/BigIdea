package DatabaseServer.datacontext;

import Models.User;

public interface IHighScoreDataContext {
    void updateScore(int userId, int score);
}
