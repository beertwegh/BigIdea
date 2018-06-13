package modelsTest;

import Models.Answer;
import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {

    private Answer testConstructorBool(boolean correct) {
        Answer answer = new Answer("answer", correct);
        return answer;
    }

    @Test
    public void testConstructorTrue() {
        Answer answer = testConstructorBool(true);
        Assert.assertEquals("answer", answer.getText());
        Assert.assertEquals(true, answer.isCorrect());
    }

    @Test
    public void testConstructorFalse() {
        Answer answer = testConstructorBool(false);
        Assert.assertEquals("answer", answer.getText());
        Assert.assertEquals(false, answer.isCorrect());
    }

}