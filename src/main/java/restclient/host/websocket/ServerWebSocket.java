package restclient.host.websocket;

import models.User;
import restclient.host.IHostGame;
import restclient.host.websocket.messagehandlers.ServerMessageProcessor;
import shared.logging.LogLevel;
import shared.logging.Logger;
import shared.websocket.interfaces.IMessageProcessor;

import javax.inject.Singleton;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;

@ServerEndpoint(value = "/toohak/")
@Singleton
public class ServerWebSocket implements IServerWebSocket {

    private static ArrayList<Session> sessions = new ArrayList<>();

    private IMessageProcessor messageHandler;

    public IMessageProcessor getMessageHandler() {
        return messageHandler;
    }


    public void setMessageHandler(IMessageProcessor messageHandler) {
        this.messageHandler = messageHandler;
    }

    private IHostGame game;

    public IHostGame getGame() {
        return game;
    }

    public ServerWebSocket(IHostGame game) {
        this.game = game;
    }

    @Override
    public void sendTo(Session session, String message) {
        if (message == null || session == null) {
            throw new IllegalArgumentException("Message or session can't be null");
        }
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            Logger.getInstance().log(e);
        }
    }

    @Override
    public void sendToUser(User user, String message) {
        String sessionId = ((ServerMessageProcessor) messageHandler).getSessionIdByUser(user);
        sendTo(getSessionFromId(sessionId), message);
    }

    @OnOpen
    public void onConnect(Session session) {
        sessions.add(session);
    }

    @OnMessage
    public void onText(String message, Session session) {
        if (messageHandler == null) {
            throw new NullPointerException("No messageHandler found");
        }
        if (message.isEmpty()) {
            sendTo(session, "No message found");
            return;
        }
        onMessageReceived(message, session.getId());
    }

    @Override
    public void onMessageReceived(String message, String sessionId) {
        messageHandler.handleMessage(message, sessionId);
    }


    @OnClose
    public void onClose(CloseReason reason, Session session) {
        sessions.remove(session);
    }

    @OnError
    public void onError(Throwable cause, Session session) {
        Logger.getInstance().log(cause.getMessage(), LogLevel.ERROR);
    }

    public Session getSessionFromId(String sessionId) {
        for (Session s : sessions) {
            if (s.getId().equals(sessionId))
                return s;
        }
        return null;
    }

    @Override
    public void broadcast(String message) {
        for (Session session : sessions) {
            sendTo(session, message);
        }
    }
}
