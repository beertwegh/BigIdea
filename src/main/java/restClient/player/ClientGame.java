package restClient.player;

import Models.Lobby;
import Models.MultipleChoice;
import Models.Question;
import restClient.restActions.GetLobbies;

import java.util.List;

public class ClientGame implements IClientGame {

    private Question question;


    @Override
    public void sendAnswer(MultipleChoice answer) {

    }

    @Override
    public List<Lobby> refreshLobbies() {
        GetLobbies getlobbies = new GetLobbies();
        return getlobbies.getLobbies();
    }

    @Override
    public void nextRound() {

    }
}