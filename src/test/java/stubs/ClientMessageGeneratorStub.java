package stubs;

import models.User;
import restclient.player.websocket.IClientMessageGenerator;
import restclient.player.websocket.IClientWebSocket;
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
