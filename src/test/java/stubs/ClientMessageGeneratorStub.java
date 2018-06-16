package stubs;

import models.User;
import restClient.player.websocket.IClientMessageGenerator;
import restClient.player.websocket.IClientWebSocket;
import shared.MultipleChoice;

public class ClientMessageGeneratorStub implements IClientMessageGenerator {
    @Override
    public void introducePlayer(User user) {
        AppFlowStack.addStack("introduceplayer");
    }

    @Override
    public void answerQuestion(MultipleChoice multipleChoice) {
        AppFlowStack.addStack(multipleChoice.toString());
    }

    @Override
    public void setSocket(IClientWebSocket socket) {

    }
}
