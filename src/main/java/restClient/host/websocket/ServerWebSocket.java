package restClient.host.websocket;

import restClient.host.IHostGame;
import shared.Logging.LogLevel;
import shared.Logging.Logger;
import shared.websocket.interfaces.IMessageHandler;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;

@ServerEndpoint(value = "/toohak/")
public class ServerWebSocket implements IServerWebSocket {

    private static ArrayList<Session> sessions = new ArrayList<>();


    private static IMessageHandler messageHandler;

    public static IMessageHandler getMessageHandler() {
        return messageHandler;
    }

    public static void setMessageHandler(IMessageHandler messageHandler) {
        ServerWebSocket.messageHandler = messageHandler;
    }

    private IHostGame game;

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
            System.out.println("Sent message to " + session.getId() + " with: \"" + message + "\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnOpen
    public void onConnect(Session session) {
        sessions.add(session);

    }

    @OnMessage
    public void onText(String message, Session session) {
        System.out.println("[Received] From : " + session.getId() + " | Content  : " + message);

        if (messageHandler == null) {
            throw new NullPointerException("No messageHandler found");
        }

        if (message.isEmpty()) {
            sendTo(session, "No message found");
            return;
        }
        messageHandler.handleMessage(message, session);
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