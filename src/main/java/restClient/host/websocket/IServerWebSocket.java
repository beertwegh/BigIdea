package restClient.host.websocket;

import javax.websocket.Session;

public interface IServerWebSocket {

    void sendTo(Session session, String message);

    void broadcast(String message);


}
