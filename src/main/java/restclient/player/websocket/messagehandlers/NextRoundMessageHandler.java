package restclient.player.websocket.messagehandlers;

import restclient.player.IClientGame;

public class NextRoundMessageHandler {

    private IClientGame game;

    public NextRoundMessageHandler(IClientGame game) {
        this.game = game;
    }

    public void nextRound() {
        game.nextRound();
    }
}
