package restClient.host.websocket.messagehandlers;

import Models.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import restClient.host.HostGame;
import shared.Logging.Logger;
import shared.websocket.interfaces.IMessageProcessor;
import shared.websocket.interfaces.Message;
import shared.websocket.interfaces.actions.AnswerQuestion;
import shared.websocket.interfaces.actions.IntroduceUser;

import java.util.HashMap;
import java.util.Map;

public class ServerMessageProcessor implements IMessageProcessor {

    public HostGame game;

    public ServerMessageProcessor(HostGame game) {
        this.game = game;
    }

    private static Map<String, User> userSessions = new HashMap<>();

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
            case ANSWERQUESTION:
                AnswerQuestionMessageHandler handler = new AnswerQuestionMessageHandler(game);
                message.parseData(AnswerQuestion.class);
                handler.handleMessage(message, getUserBySessionId(sessionId));
                break;
            case INTRODUCEUSER:
                message.parseData(IntroduceUser.class);
                userSessions.put(sessionId, ((IntroduceUser) message.getData()).getUser());
                break;
        }
    }

    private User getUserBySessionId(String sessionId) {
        return userSessions.get(sessionId);
    }


}
