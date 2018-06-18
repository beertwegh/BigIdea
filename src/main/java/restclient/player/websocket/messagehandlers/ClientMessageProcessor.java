package restclient.player.websocket.messagehandlers;

import com.google.gson.Gson;
import restclient.player.IClientGame;
import shared.websocket.interfaces.IMessageProcessor;
import shared.websocket.interfaces.Message;
import shared.websocket.interfaces.actions.ReplyAnswerQuestion;

public class ClientMessageProcessor implements IMessageProcessor {
    private IClientGame game;

    public ClientMessageProcessor(IClientGame game) {
        this.game = game;
    }

    @Override
    public void handleMessage(String json, String sessionId) {
        Gson gson = new Gson();
        Message message = gson.fromJson(json, Message.class);
        if (message == null) {
            throw new IllegalArgumentException("message can't be null");
        }
        switch (message.getAction()) {
            case STARTGAME:
                StartGameMessageHandler startGameMessageHandler = new StartGameMessageHandler(game);
                startGameMessageHandler.startGame();
                break;
            case NEXTROUND:
                NextRoundMessageHandler nextRoundMessageHandler = new NextRoundMessageHandler(game);
                nextRoundMessageHandler.nextRound();
                break;
            case REPLYANSWERQUESTION:
                ReplyAnswerQuestion reply = gson.fromJson(message.getContent(), ReplyAnswerQuestion.class);
                ReplyAnswerQuestionMessageHandler replyAnswerQuestionMessageHandler = new ReplyAnswerQuestionMessageHandler(game);
                replyAnswerQuestionMessageHandler.handleReply(reply.isCorrect());
                break;
            case ENDGAME:
                EndGameMessageHandler handler = new EndGameMessageHandler(game);
                handler.endGame();
                break;
            default:
                break;
        }
    }
}
