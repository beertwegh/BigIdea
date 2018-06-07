package restClient.player.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import restClient.host.websocket.messagehandlers.AnswerQuestionMessageHandler;
import shared.Logging.Logger;
import shared.websocket.interfaces.Action;
import shared.websocket.interfaces.IMessageHandler;
import shared.websocket.interfaces.Message;

public class ClientMessageHandler implements IMessageHandler {
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
            case NEXTROUND:
                break;
        }
    }
}

