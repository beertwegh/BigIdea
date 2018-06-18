package gametests;

import models.Answer;
import models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import restClient.host.HostGame;
import shared.MultipleChoice;
import stubs.AppFlowStack;
import stubs.ServerMessageGeneratorStub;
import stubs.ToohakGameStub;

import java.util.List;

public class HostGameTest {
    private HostGame game;

    @Before
    public void init() {
        AppFlowStack.clearStack();
        game = new HostGame();
        game.setIToohakGame(new ToohakGameStub());
    }

    @Test
    public void testNextRound() {
        game.setMessageGenerator(new ServerMessageGeneratorStub());
        game.nextRound();
        Assert.assertEquals(1, AppFlowStack.getStack().size());
        Assert.assertEquals("nextroundGenerated", AppFlowStack.getStack().get(0));
    }

    @Test
    public void testStartGame() {
        game.setMessageGenerator(new ServerMessageGeneratorStub());
        game.startGame();
        Assert.assertEquals(2, AppFlowStack.getStack().size());
        Assert.assertEquals("startgameGenerated", AppFlowStack.getStack().get(0));
        Assert.assertEquals("nextroundGenerated", AppFlowStack.getStack().get(1));
    }

    @Test
    public void testProcessAnswerQuestion() {
        User user = new User(0, "username", "test@gmail.com", 0);
        game.setMessageGenerator(new ServerMessageGeneratorStub());
        game.nextRound();
        List<Answer> answers = game.getQuestions().get(game.getCurrentRandom()).getAnswers();
        Answer answerCorrect = null;
        for (Answer answer : answers) {
            if (answer.isCorrect()) {
                answerCorrect = answer;
            }
        }
        int index = answers.indexOf(answerCorrect);
        game.processAnswerQuestion(user, MultipleChoice.values()[index]);
        Assert.assertEquals(2, AppFlowStack.getStack().size());
        Assert.assertEquals("true", AppFlowStack.getStack().get(1));
        if (index > 0) {
            game.processAnswerQuestion(user, MultipleChoice.values()[0]);
        } else {
            game.processAnswerQuestion(user, MultipleChoice.values()[1]);
        }
        Assert.assertEquals(3, AppFlowStack.getStack().size());
        Assert.assertEquals("false", AppFlowStack.getStack().get(2));
    }

    @Test
    public void testGameEnded() {
        game.setMessageGenerator(new ServerMessageGeneratorStub());
        game.gameEnded();
        Assert.assertEquals(1, AppFlowStack.getStack().size());
        Assert.assertEquals("endgameGenerated", AppFlowStack.getStack().get(0));
    }
}