package restclient.host.websocket.messagehandlers;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.gson.Gson;
import models.User;
import restclient.host.IHostGame;
import shared.websocket.interfaces.IMessageProcessor;
import shared.websocket.interfaces.Message;
import shared.websocket.interfaces.actions.AnswerQuestion;
import shared.websocket.interfaces.actions.IntroduceUser;

public class ServerMessageProcessor implements IMessageProcessor {

    private IHostGame game;

    public ServerMessageProcessor(IHostGame game) {
        this.game = game;
    }

    private static BiMap<String, User> userSessions = HashBiMap.create();

    @Override
    public void handleMessage(String json, String sessionId) {
        Gson gson = new Gson();
        Message message = gson.fromJson(json, Message.class);

        if (message == null) {
            throw new IllegalArgumentException("message can't be null");
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
                game.processPlayerJoined(((IntroduceUser) message.getData()).getUser());
                break;
            default:
                break;
        }
    }

    public String getSessionIdByUser(User user) {
        return userSessions.inverse().get(user);
    }

    public User getUserBySessionId(String sessionId) {
        return userSessions.get(sessionId);
    }
}