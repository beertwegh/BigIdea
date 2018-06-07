package shared.websocket.interfaces.actions;

import shared.MultipleChoice;

public class AnswerQuestion implements IAction {
    public MultipleChoice answer;

    public AnswerQuestion(MultipleChoice answer) {
        this.answer = answer;
    }

    public MultipleChoice getAnswer() {
        return answer;
    }
}
