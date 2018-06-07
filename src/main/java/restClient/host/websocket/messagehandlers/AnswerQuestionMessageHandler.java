package restClient.host.websocket.messagehandlers;

import Models.User;
import org.eclipse.jetty.server.Server;
import restClient.host.HostGame;
import shared.websocket.interfaces.Message;
import shared.websocket.interfaces.actions.AnswerQuestion;

public class AnswerQuestionMessageHandler {

    private HostGame game;

    public AnswerQuestionMessageHandler(HostGame game) {
        this.game = game;
    }

    public void handleMessage(Message message, User user) {

        AnswerQuestion answerQuestion = (AnswerQuestion) message.getData();
        game.processAnswerQuestion(user, answerQuestion.getAnswer());
    }
}