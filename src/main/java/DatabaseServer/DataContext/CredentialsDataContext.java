package DatabaseServer.DataContext;

import DatabaseServer.Specifiables.Specifiable;
import Models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CredentialsDataContext extends AbstractDataContext implements IDataContext<User> {
    public Object findOne(int id) {
        return null;
    }

    public Object findOne(Specifiable specifiable) {
        return null;
    }

    public void save(User user) {
        try {
            connection = DriverManager.getConnection(connString);
            queryString = "insert into Credentials (username, email, password) values (?, ?, ?)";
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

    public List<User> findAll() {
        List<User> all = new ArrayList<User>();
        try {
            connection = DriverManager.getConnection(connString);
            queryString = "select id, username, email, hs.score from Credentials c left join HighScore hs on c.id = hs.CredentialsId";
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
