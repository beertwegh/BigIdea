package restClient.player.websocket;

import com.google.gson.Gson;
import shared.Logging.LogLevel;
import shared.Logging.Logger;
import shared.websocket.interfaces.IMessageProcessor;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

@ClientEndpoint
public class ClientWebSocket implements IClientWebSocket {

    private Session session;
    private IMessageProcessor handler;

    @Override
    public void start(String ip) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            String uri = "ws://" + ip + "/toohak/";
            container.connectToServer(this, new URI(uri));
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

    @Override
    public void setMessageHandler(IMessageProcessor handler) {
        this.handler = handler;
    }

    @OnOpen
    public void onWebSocketConnect(Session session) {
        this.session = session;

    }

    @OnMessage
    public void onWebSocketText(String message, Session session) {
        onWebSocketMessageReceived(message, session.getId());
    }

    public void onWebSocketMessageReceived(String message, String sessionId) {
        if (handler == null) {
            throw new NullPointerException("messageHandler is null");
        }
        if (!message.isEmpty()) {
            handler.handleMessage(message, sessionId);
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

    @Override
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