package stubs;

import models.User;
import restclient.host.websocket.IServerWebSocket;
import shared.websocket.interfaces.IMessageProcessor;

import javax.websocket.Session;

public class ServerWebSocketStub implements IServerWebSocket {
    @Override
    public void setMessageHandler(IMessageProcessor messageHandler) {

    }

    @Override
    public void sendTo(Session session, String message) {

    }

    @Override
    public void sendToUser(User user, String message) {
        AppFlowStack.addStack(user.getUsername() + message);
    }

    @Override
    public void onMessageReceived(String message, String sessionId) {

    }

    @Override
    public void broadcast(String message) {
        AppFlowStack.addStack(message);
    }
}
