package stubs;

import Models.User;
import interfaces.IToohakGame;
import restClient.host.HostGame;
import shared.MultipleChoice;

public class HostGameStub extends HostGame {
    public HostGameStub(IToohakGame game) {
        super(game);
    }

    @Override
    public void processAnswerQuestion(User user, MultipleChoice answer) {
        AppFlowStack.addStack(answer.toString());
    }
}