package stubs;

import models.User;
import restclient.host.HostGame;
import shared.MultipleChoice;

public class HostGameStub extends HostGame {

    @Override
    public void processAnswerQuestion(User user, MultipleChoice answer) {
        AppFlowStack.addStack(answer.toString());
    }
    @Override
    public void nextRound() {
        AppFlowStack.addStack("nextround");
    }
    @Override
    public void processPlayerJoined(User user) {
        AppFlowStack.addStack(user.getUsername());
    }
}