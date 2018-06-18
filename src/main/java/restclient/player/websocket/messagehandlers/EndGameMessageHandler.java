package restclient.player.websocket.messagehandlers;

import restclient.player.IClientGame;

public class EndGameMessageHandler {
    private IClientGame game;

    public EndGameMessageHandler(IClientGame game) {
        this.game = game;
    }

    public void endGame() {
        game.processEndGame();
    }
}
