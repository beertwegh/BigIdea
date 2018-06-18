package stubs;

import com.google.gson.Gson;
import restClient.player.websocket.IClientWebSocket;
import shared.websocket.interfaces.IMessageProcessor;

public class ClientWebSocketStub implements IClientWebSocket {
    @Override
    public void start(String ip) {

    }

    @Override
    public void stop() {

    }

    @Override
    public void send(Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        AppFlowStack.addStack(json);
    }

    @Override
    public void setMessageHandler(IMessageProcessor handler) {

    }

    @Override
    public void onWebSocketMessageReceived(String message, String sessionId) {

    }
}
