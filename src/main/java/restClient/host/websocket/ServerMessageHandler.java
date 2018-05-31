package restClient.host.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import shared.Logging.Logger;
import shared.websocket.interfaces.IMessageHandler;
import shared.websocket.interfaces.Message;

public class ServerMessageHandler implements IMessageHandler {

    @Override
    public void handleMessage(String json, String session) {
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
            case "AnswerQuestion":
                break;

        }
    }
}
