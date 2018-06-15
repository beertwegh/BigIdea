package models;

public class Answer {

    private String text;
    private boolean correct;

    public Answer(String text, boolean correct) {
        if (text == null || text.trim().equals("")) {
            throw new IllegalArgumentException("text isn't filled in");
        } else {
            this.text = text;
        }
        this.correct = correct;
    }

    public boolean isCorrect() {
        return correct;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}