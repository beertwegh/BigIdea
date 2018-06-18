package stubs;

import models.User;
import restClient.host.websocket.IServerMessageGenerator;
import restClient.host.websocket.IServerWebSocket;

public class ServerMessageGeneratorStub implements IServerMessageGenerator {
    @Override
    public void setSocket(IServerWebSocket socket) {

    }

    @Override
    public void startGame() {
        AppFlowStack.addStack("startgameGenerated");
    }

    @Override
    public void nextRound() {
        AppFlowStack.addStack("nextroundGenerated");
    }

    @Override
    public void endGame() {
        AppFlowStack.addStack("endgameGenerated");
    }

    @Override
    public void replyAnswerQuestion(boolean correct, User user) {
        if (correct) {
            AppFlowStack.addStack("true");
        } else {

            AppFlowStack.addStack("false");
        }
    }
}
