package restClient.player;

import interfaces.IToohakGame;
import models.Lobby;
import restClient.player.websocket.ClientWebSocket;
import restClient.player.websocket.IClientMessageGenerator;
import restClient.player.websocket.messagehandlers.ClientMessageProcessor;
import restClient.restActions.GetLobbies;
import shared.MultipleChoice;

import java.util.List;

public class ClientGame implements IClientGame {

    private IToohakGame game;

    private ClientWebSocket socket;

    private IClientMessageGenerator messageGenerator;

    public ClientGame(IToohakGame game) {
        this.game = game;
    }

    @Override
    public void setMessageGenerator(IClientMessageGenerator messageGenerator) {
        this.messageGenerator = messageGenerator;
    }

    @Override
    public void sendAnswer(MultipleChoice answer) {
        messageGenerator.answerQuestion(answer);
    }


    @Override
    public List<Lobby> refreshLobbies() {
        GetLobbies getlobbies = new GetLobbies();
        return getlobbies.getLobbies();
    }

    @Override
    public void joinLobby(Lobby lobby) {
        socket = new ClientWebSocket();
        socket.setMessageHandler(new ClientMessageProcessor(this));
        socket.start(lobby.getIp());
        messageGenerator.setSocket(socket);
        messageGenerator.introducePlayer(game.getUser());
    }

    @Override
    public void processStartGame() {
        game.processStartGame();
    }

    @Override
    public void nextRound() {
        game.processNextRound(this);
    }

    @Override
    public void processAnswerReply(boolean correct) {
        game.processAnswerReply(correct);
    }

    @Override
    public void processEndGame() {
        game.processEndGame(this);
    }
}