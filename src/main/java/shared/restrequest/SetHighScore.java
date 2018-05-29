package shared.restrequest;

public class SetHighScore {
    private int id;
    private int score;

    public SetHighScore(int id, int score) {
        this.id = id;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }
}
