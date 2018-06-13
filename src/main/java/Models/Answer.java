package Models;

public class Answer {

    private String text;
    private boolean correct;

    public Answer(String text, boolean correct) {
        if (text != null || text.trim().equals("")) {
            this.text = text;
        } else {
            throw new IllegalArgumentException("text isn't filled in");
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