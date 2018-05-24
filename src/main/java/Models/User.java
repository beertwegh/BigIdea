package Models;

import Utilities.Hashing;

public class User {
    private int id;
    private String username;
    private String email;
    private int score;
    private String password;

    public User(int id, String username, String email, int score) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.score = score;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        setPassword(password);
    }

    public void setPassword(String password) {
        this.password = Hashing.get_SHA_512_SecurePassword(password, "salty");
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getScore() {
        return score;
    }
}