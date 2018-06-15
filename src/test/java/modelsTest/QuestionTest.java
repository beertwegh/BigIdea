package modelsTest;

import models.Answer;
import models.Question;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

public class QuestionTest {
    private List<Answer> answers = new ArrayList<>();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        answers.add(new Answer("test1", true));
        answers.add(new Answer("test2", false));
        answers.add(new Answer("test3", false));
        answers.add(new Answer("test4", false));

    }

    @Test
    public void testConstructor() {
        Question question = new Question("test", answers);
        Assert.assertEquals("test", question.getText());
        Assert.assertEquals(answers, question.getAnswers());
    }

    @Test
    public void testConstructorTextNull() {
        exception.expect(IllegalArgumentException.class);
        Question question = new Question(null, answers);
    }

    @Test
    public void testConstructorAnswersNull() {
        exception.expect(IllegalArgumentException.class);
        Question question = new Question("test", null);
    }
}
