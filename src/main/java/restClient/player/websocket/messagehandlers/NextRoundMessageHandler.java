package restClient.player.websocket.messagehandlers;

import restClient.player.IClientGame;
import shared.websocket.interfaces.Message;

public class NextRoundMessageHandler {

    private IClientGame game;

    public NextRoundMessageHandler(IClientGame game) {
        this.game = game;
    }

    public void nextRound() {
        game.nextRound();
    }
}
