package stubs;

import interfaces.IToohakGame;
import models.Lobby;
import restClient.player.IClientGame;
import restClient.player.websocket.IClientMessageGenerator;
import shared.MultipleChoice;

import java.util.List;

public class ClientGameStub implements IClientGame
{
    @Override
    public void setMessageGenerator(IClientMessageGenerator messageGenerator) {

    }

    @Override
    public void sendAnswer(MultipleChoice answer) {

    }

    @Override
    public List<Lobby> refreshLobbies() {
        return null;
    }

    @Override
    public void joinLobby(Lobby lobby) {

    }

    @Override
    public void processStartGame() {

    }

    @Override
    public void processAnswerReply(boolean correct) {

    }

    @Override
    public void processEndGame() {

    }

    @Override
    public void nextRound() {
    AppFlowStack.addStack("nextround");
    }

    @Override
    public void setIToohakGame(IToohakGame game) {

    }
}
