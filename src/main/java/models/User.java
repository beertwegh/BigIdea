package models;

import utilities.Hashing;

public class User {
    private int id;
    private String username;
    private String email;
    private int score;
    private String password;

    public User(int id, String username, String email, Integer score, String password) {
        this.id = id;
        setUsername(username);
        setEmail(email);
        setScore(score);
        setPasswordUnhashed(password);
    }

    public User(int id, String username, String email, Integer score) {
        this.id = id;
        setUsername(username);
        setEmail(email);
        this.score = score;
    }

    public User(String username, String email, String password) {
        setUsername(username);
        setEmail(email);
        setPassword(password);
    }

    private void setUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("username is null");
        } else
            this.username = username;
    }

    private void setEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("email is null");
        } else
            this.email = email;
    }

    public void setScore(Integer score) {
        if (score == null)
            this.score = 0;
        else
            this.score = score;
    }

    private void setPasswordUnhashed(String password) {
        if (password == null)
            throw new IllegalArgumentException("password cant be null");
        else
            this.password = password;
    }

    public void setPassword(String password) {
        if (password != null)
            this.password = Hashing.getSha512Securepassword(password, "salty");
        else
            throw new IllegalArgumentException("password can't be null");
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

    @Override
    public String toString() {
        return username;
    }
}