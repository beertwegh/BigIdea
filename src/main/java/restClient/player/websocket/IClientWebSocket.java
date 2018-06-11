package restClient.player.websocket;

import javax.websocket.Session;

public interface IClientWebSocket {
    void start(String ip);


    void stop();

    void send(Object object);

    void onWebSocketMessageReceived(String message, Session session);
}
