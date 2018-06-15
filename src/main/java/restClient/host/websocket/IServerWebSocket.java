package restClient.host.websocket;

import Models.User;
import shared.websocket.interfaces.IMessageProcessor;

import javax.websocket.Session;

public interface IServerWebSocket {

    void setMessageHandler(IMessageProcessor messageHandler);

    void sendTo(Session session, String message);

    void sendToUser(User user, String message);

    void onMessageReceived(String message, String sessionId);

    void broadcast(String message);


}
