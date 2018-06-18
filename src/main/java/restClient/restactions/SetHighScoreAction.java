package restClient.restactions;

import shared.restrequest.SetHighScore;

public class SetHighScoreAction extends BaseAction<SetHighScore> {

    public String setHighScore(SetHighScore setHighScore) {
        return super.baseMethod(setHighScore, "http://rest.basvdeertwegh.nl/highscore/set");
    }
}