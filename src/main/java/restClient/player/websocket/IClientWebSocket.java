package restClient.player.websocket;

import shared.websocket.interfaces.IMessageProcessor;

import javax.websocket.Session;

public interface IClientWebSocket {
    void start(String ip);


    void stop();

    void send(Object object);

    void setMessageHandler(IMessageProcessor handler);

    void onWebSocketMessageReceived(String message, String sessionId);
}
