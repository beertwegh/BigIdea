package shared.websocket.interfaces.actions;

import models.User;

public class IntroduceUser implements IAction {
    private User user;

    public User getUser() {
        return user;
    }

    public IntroduceUser(User user) {
        this.user = user;
    }
}
