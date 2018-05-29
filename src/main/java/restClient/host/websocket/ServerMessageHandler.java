package restClient.host.websocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import shared.Logging.Logger;
import shared.websocket.interfaces.IMessageHandler;
import shared.websocket.interfaces.Message;
import shared.websocket.interfaces.actions.StartGame;

import javax.websocket.Session;
import java.io.IOException;

public class ServerMessageHandler implements IMessageHandler {

    @Override
    public void handleMessage(String json, Session session) {
        Gson gson = new Gson();
        Message message = null;

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();

        try {
            message = gson.fromJson(jsonObject, Message.class);
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
            sendMessage(new Message("error", ex.getMessage()), session);
        }
        switch (message.getAction()) {
            case "StartGame":
                message.parseData(StartGame.class);

        }
    }







    private void sendMessage(Message message, Session session) {
        Gson json = new GsonBuilder().create();

        if (message == null || session == null) {
            throw new IllegalArgumentException("Message or session can't be null");
        }
        try {
            String jsonToSend = json.toJson(message);
            session.getBasicRemote().sendText(jsonToSend);
            System.out.println("Send message to " + session.getId() + " with: \"" + jsonToSend + "\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
