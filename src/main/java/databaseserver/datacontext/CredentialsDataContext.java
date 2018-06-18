package databaseserver.datacontext;

import databaseserver.speicifiables.Specifiable;
import models.Admin;
import models.User;
import shared.logging.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CredentialsDataContext extends AbstractDataContext implements ICredentialsDataContext {
    private String usernameString = "username";
    private String emailString = "email";

    public CredentialsDataContext(String differentConnString) {
        super(differentConnString);
    }

    public CredentialsDataContext() {
    }

    @Override
    public User findOne(Specifiable specifiable) {
        try {
            connection = DriverManager.getConnection(connString);
            queryString = "SELECT id, username, email, password, admin, hs.score FROM Credentials c LEFT JOIN HighScore hs ON c.id = hs.CredentialsId WHERE " + specifiable.getSpecifiable() + " = ?";
            PreparedStatement stmt = connection.prepareStatement(queryString);
            stmt.setString(1, specifiable.getParameter());
            try (ResultSet rset = stmt.executeQuery()) {
                if (!rset.next()) {
                    return null;
                } else {
                    rset.first();
                    if (rset.getBoolean("admin")) {
                        return new Admin(rset.getInt("id"), rset.getString(usernameString), rset.getString(emailString), rset.getInt("hs.score"), rset.getString("password"));
                    }
                    return new User(rset.getInt("id"), rset.getString(usernameString), rset.getString(emailString), rset.getInt("hs.score"), rset.getString("password"));
                }
            }
        } catch (SQLException e) {
            Logger.getInstance().log(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                Logger.getInstance().log(e);
            }
        }
        return null;
    }

    @Override
    public void save(User user) {
        try {
            connection = DriverManager.getConnection(connString);
            queryString = "INSERT INTO Credentials (username, email, password) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(queryString);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            Logger.getInstance().log(e);
            throw new IllegalArgumentException("something went wrong");
        }
    }

    @Override
    public List<User> findAll() {
        List<User> all = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(connString);
            queryString = "SELECT id, username, email, hs.score FROM Credentials c LEFT JOIN HighScore hs ON c.id = hs.CredentialsId";
            Statement stmt = connection.createStatement();
            ResultSet rset = stmt.executeQuery(queryString);
            while (rset.next()) {
                int id = rset.getInt("id");
                String email = rset.getString(this.emailString);
                String username = rset.getString(this.usernameString);
                int score = rset.getInt("score");
                all.add(new User(id, username, email, score));
            }
            connection.close();
            return all;
        } catch (SQLException e) {
            Logger.getInstance().log(e);
        }
        return all;
    }
}