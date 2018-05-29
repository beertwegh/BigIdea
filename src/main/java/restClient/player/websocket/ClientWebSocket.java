package restClient.player.websocket;

import com.google.gson.Gson;
import jersey.repackaged.com.google.common.collect.ImmutableSetMultimap;
import shared.Logging.LogLevel;
import shared.Logging.Logger;
import shared.websocket.interfaces.IMessageHandler;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

public class ClientWebSocket implements IClientWebSocket {

    private Session session;
    private IMessageHandler handler;
    private static ClientWebSocket instance = null;

    public static ClientWebSocket getInstance() {
        if (instance == null) {
            instance = new ClientWebSocket();
        }
        return instance;
    }

    @Override
    public void start(String ip) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI(ip));
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
        }
    }

    @Override
    public void stop() {
        try {
            if (session != null) {
                session.close();
            }
        } catch (Exception ex) {
            Logger.getInstance().log(ex);
        }
    }

    public void setMessageHandler(IMessageHandler handler) {
        this.handler = handler;
    }

    @OnOpen
    public void onWebSocketConnect(Session session) {
        this.session = session;
    }

    @OnMessage
    public void onWebSocketText(String message, Session session) {
        onWebSocketMessageReceived(message, session);
    }

    public void onWebSocketMessageReceived(String message, Session session) {
        if (handler == null) {
            throw new NullPointerException("messageHandler is null");
        }
        if (!message.isEmpty()) {
            handler.handleMessage(message, session);
        }
    }

    @OnError
    public void onWebSocketError(Session session, Throwable cause) {
        Logger.getInstance().log(cause.getMessage(), LogLevel.ERROR);
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason) {
        session = null;
    }

    public void send(Object object) {
        Gson gson = new Gson();
        String jsontosend = gson.toJson(object);
        sendMessageToServer(jsontosend);
    }

    private void sendMessageToServer(String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            Logger.getInstance().log(ex);
        }
    }

}