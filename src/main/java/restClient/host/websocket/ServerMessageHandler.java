package restClient.host.websocket;

import Models.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import restClient.host.IHostGame;
import shared.Logging.Logger;
import shared.websocket.interfaces.IMessageHandler;
import shared.websocket.interfaces.Message;
import shared.websocket.interfaces.actions.AnswerQuestion;

public class ServerMessageHandler implements IMessageHandler {

    public IHostGame game;

    public ServerMessageHandler(IHostGame game) {
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
            case "AnswerQuestion":
                message.parseData(AnswerQuestion.class);
                answerQuestion(sessionId, (AnswerQuestion) message.getData());
                break;
        }
    }

    private void answerQuestion(String sessionId, AnswerQuestion data) {
        game.processAnswerQuestion(new User(null,null,null), data.getAnswer());
    }
}
