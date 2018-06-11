package shared.websocket.interfaces.actions;

public class ReplyAnswerQuestion implements IAction {
    private boolean correct;

    public ReplyAnswerQuestion(boolean correct) {
        this.correct = correct;
    }

    public boolean isCorrect() {
        return correct;
    }
}
