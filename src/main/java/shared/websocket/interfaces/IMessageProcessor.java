package shared.websocket.interfaces;

public interface IMessageProcessor {

    void handleMessage(String message, String sessionId);

}