package restClient.host.websocket.messagehandlers;

import Models.User;
import restClient.host.IHostGame;
import shared.websocket.interfaces.Message;
import shared.websocket.interfaces.actions.AnswerQuestion;

public class AnswerQuestionMessageHandler{

    private IHostGame game;

    public AnswerQuestionMessageHandler(IHostGame game) {
        this.game = game;
    }

    public void handleMessage(Message message, User user) {
        AnswerQuestion answerQuestion = (AnswerQuestion) message.getData();
        game.processAnswerQuestion(user, answerQuestion.getAnswer());
    }
}