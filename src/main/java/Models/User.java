package Models;

public class User {
    private int id;
    private String username;
    private String email;
    private int score;

    public User(int id, String username, String email, int score) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.score = score;
    }
}