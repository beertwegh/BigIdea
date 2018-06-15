package models;

import java.util.List;

public class Question {

    private String text;
    private List<Answer> answers;

    public Question(String text, List<Answer> answers) {
        if (text != null && answers != null) {
            this.text = text;
            this.answers = answers;
        } else {
            throw new IllegalArgumentException("something went wrong");
        }
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public String getText() {
        return text;
    }
}