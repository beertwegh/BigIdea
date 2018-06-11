package restClient.player.websocket.messagehandlers;

import restClient.player.IClientGame;

public class StartGameMessageHandler {
    private IClientGame game;

    public StartGameMessageHandler(IClientGame game) {
        this.game = game;
    }

    public void startGame() {
        game.processStartGame();
    }
}
