package restClient.player.websocket;

import models.User;
import shared.MultipleChoice;

public interface IClientMessageGenerator {

    void introducePlayer(User user);

    void answerQuestion(MultipleChoice multipleChoice);
}
