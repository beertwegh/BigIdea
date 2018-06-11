package restClient.player.websocket.messagehandlers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import restClient.player.IClientGame;
import shared.Logging.Logger;
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
        Message message = null;
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
        try {
            message = gson.fromJson(jsonObject, Message.class);
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
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
        }
    }
}
