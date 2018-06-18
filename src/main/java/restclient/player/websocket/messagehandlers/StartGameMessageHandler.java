package restclient.player.websocket.messagehandlers;

import restclient.player.IClientGame;

public class StartGameMessageHandler {
    private IClientGame game;

    public StartGameMessageHandler(IClientGame game) {
        this.game = game;
    }

    public void startGame() {
        game.processStartGame();
    }
}
