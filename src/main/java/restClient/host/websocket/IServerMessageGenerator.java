package restClient.host.websocket;

import models.User;

public interface IServerMessageGenerator {

    void startGame();

    void nextRound();

    void endGame();

    void replyAnswerQuestion(boolean correct, User user);
}
