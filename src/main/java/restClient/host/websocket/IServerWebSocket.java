package restClient.host.websocket;

import Models.User;

import javax.websocket.Session;

public interface IServerWebSocket {

    void sendTo(Session session, String message);

    void sendToUser(User user, String message);

    void broadcast(String message);


}
