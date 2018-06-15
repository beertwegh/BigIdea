package restClient.player.websocket.messagehandlers;

import restClient.player.IClientGame;

public class EndGameMessageHandler {
    private IClientGame game;

    public EndGameMessageHandler(IClientGame game) {
        this.game = game;
    }
    public void endGame() {
        game.processEndGame();
    }
}
