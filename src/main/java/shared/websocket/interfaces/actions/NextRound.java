package shared.websocket.interfaces.actions;

import Models.Question;

public class NextRound implements IAction {
    private Question question;

    public Question getQuestion() {
        return question;
    }

    public NextRound(Question question) {
        this.question = question;
    }
}
