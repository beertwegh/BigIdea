package Models;

public class Answer {

    private String text;
    private boolean correct;

    public Answer(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    public boolean isCorrect() {
        return correct;
    }

    @Override
    public String toString() {
        return text;
    }
}