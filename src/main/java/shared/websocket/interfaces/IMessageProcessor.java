package shared.websocket.interfaces;

import javax.websocket.Session;

public interface IMessageProcessor {

    void handleMessage(String message, String sessionId);

}