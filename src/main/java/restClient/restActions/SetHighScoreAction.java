package restClient.restActions;

import shared.restrequest.SetHighScore;

public class SetHighScoreAction extends BaseAction<SetHighScore> {

    public String setHighScore(SetHighScore setHighScore) {
        return super.baseMethod(setHighScore, "http://localhost:8090/highscore/set");
    }
}