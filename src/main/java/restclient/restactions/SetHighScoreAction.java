package restclient.restactions;

import shared.restrequest.SetHighScore;

public class SetHighScoreAction implements BaseAction<SetHighScore> {

    public String setHighScore(SetHighScore setHighScore) {
        return baseMethod(setHighScore, "http://rest.basvdeertwegh.nl/highscore/set");
    }
}