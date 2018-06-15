package models;

public class Admin extends User {
    public Admin(int id, String username, String email, Integer score, String password) {
        super(id, username, email, score, password);
    }

    public Admin(int id, String username, String email, int score) {
        super(id, username, email, score);
    }

    public Admin(String username, String email, String password) {
        super(username, email, password);
    }
}
