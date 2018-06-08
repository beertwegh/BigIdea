package restClient.host.websocket;

import Models.User;

public interface IServerMessageGenerator {

    void startGame();

    void nextRound();

    void endGame();

    void replyAnswerQuestion(boolean correct, User user);
}
