package restClient.player.websocket;

import models.User;
import shared.MultipleChoice;
import shared.websocket.interfaces.Message;
import shared.websocket.interfaces.actions.Action;
import shared.websocket.interfaces.actions.AnswerQuestion;
import shared.websocket.interfaces.actions.IntroduceUser;

public class ClientMessageGenerator implements IClientMessageGenerator {
    private IClientWebSocket socket;

    public ClientMessageGenerator(IClientWebSocket socket) {
        this.socket = socket;
    }

    @Override
    public void introducePlayer(User user) {
        IntroduceUser introduceUser = new IntroduceUser(user);
        Message message = new Message(Action.INTRODUCEUSER, introduceUser);
        socket.send(message);
    }

    @Override
    public void answerQuestion(MultipleChoice answer) {
        AnswerQuestion answerQuestion = new AnswerQuestion(answer);
        Message message = new Message(Action.ANSWERQUESTION, answerQuestion);
        socket.send(message);
    }
}
