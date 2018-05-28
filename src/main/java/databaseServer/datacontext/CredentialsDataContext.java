package databaseServer.datacontext;

import databaseServer.speicifiables.Specifiable;
import Models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CredentialsDataContext extends AbstractDataContext implements ICredentialsDataContext {

    @Override
    public User findOne(Specifiable specifiable) {
        try {
            connection = DriverManager.getConnection(connString);
            queryString = "SELECT id, username, email, password, hs.score FROM Credentials c LEFT JOIN HighScore hs ON c.id = hs.CredentialsId WHERE " + specifiable.getSpecifiable() + " = ?";
            PreparedStatement stmt = connection.prepareStatement(queryString);
            stmt.setString(1, specifiable.getParameter());
            ResultSet rset = stmt.executeQuery();
            if (!rset.next()) {
                return null;
            } else {
                rset.first();
                User user = new User(rset.getInt("id"), rset.getString("username"), rset.getString("email"), rset.getInt("hs.score"), rset.getString("password"));
                connection.close();
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
            e.printStackTrace();
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
                String email = rset.getString("email");
                String username = rset.getString("username");
                int score = rset.getInt("score");
                all.add(new User(id, username, email, score));
            }
            connection.close();
            return all;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }

}
