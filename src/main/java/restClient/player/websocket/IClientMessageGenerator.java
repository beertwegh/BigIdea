package restClient.player.websocket;

import Models.User;
import shared.MultipleChoice;

public interface IClientMessageGenerator {

    void introducePlayer(User user);

    void answerQuestion(MultipleChoice multipleChoice);
}
