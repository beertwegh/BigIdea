package shared.websocket.interfaces;

import javax.websocket.Session;

public interface IMessageHandler {

    void handleMessage(String message, String sessionId);
}
