package restClient.host.websocket.messagehandlers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import restClient.host.HostGame;
import shared.Logging.Logger;
import shared.websocket.interfaces.IMessageHandler;
import shared.websocket.interfaces.Message;

public class ServerMessageHandler implements IMessageHandler {

    public HostGame game;

    public ServerMessageHandler(HostGame game) {
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
            case ANSWERQUESTION:
                AnswerQuestionMessageHandler handler = new AnswerQuestionMessageHandler(game);
                handler.handleMessage(message, sessionId);
                break;
        }
    }


}
