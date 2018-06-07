package restClient.host.websocket.messagehandlers;

import Models.User;
import restClient.host.HostGame;
import shared.websocket.interfaces.Message;
import shared.websocket.interfaces.actions.AnswerQuestion;

public class AnswerQuestionMessageHandler {

    private HostGame game;

    public AnswerQuestionMessageHandler(HostGame game) {
        this.game = game;
    }

    public void handleMessage(Message message, String sessionId) {
        AnswerQuestion answerQuestion = (AnswerQuestion) message.getData();
        game.processAnswerQuestion(new User(null, null, null), answerQuestion.getAnswer());
    }
}