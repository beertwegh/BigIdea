package restClient.player.websocket.messagehandlers;

import restClient.player.IClientGame;

public class ReplyAnswerQuestionMessageHandler {
    private IClientGame game;

    public ReplyAnswerQuestionMessageHandler(IClientGame game) {
        this.game = game;
    }

    public void handleReply(boolean correct) {
        game.processAnswerReply(correct);
    }
}
