package stubs;

import Models.User;
import restClient.host.websocket.IServerMessageGenerator;

public class ServerMessageGeneratorStub implements IServerMessageGenerator {
    @Override
    public void startGame() {
        
    }

    @Override
    public void nextRound() {

    }

    @Override
    public void endGame() {

    }

    @Override
    public void replyAnswerQuestion(boolean correct, User user) {

    }
}
