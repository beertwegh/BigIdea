package Models;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private String text;
    private List<Answer> answers;

    public Question(String text, List<Answer> answers) {
        this.text = text;
        this.answers = answers;
    }
}