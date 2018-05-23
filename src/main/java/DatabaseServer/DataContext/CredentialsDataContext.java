package DatabaseServer.DataContext;

import DatabaseServer.Specifiables.Specifiable;
import Models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CredentialsDataContext extends AbstractDataContext implements IDataContext {
    public Object findOne(int id) {
        return null;
    }

    public Object findOne(Specifiable specifiable) {
        return null;
    }

    public void save(Object item) {

    }

    public List<User> findAll() {
        List<User> all = new ArrayList<User>();
        try {
            System.out.println("here");
            Connection connection = DriverManager.getConnection(connString);
            System.out.println("here2");
            String queryString = "select id, username, email, hs.score from Credentials c left join HighScore hs on c.id = hs.CredentialsId";
            Statement stmt = connection.createStatement();
            ResultSet rset = stmt.executeQuery(queryString);
            while (rset.next()) {
                int id = rset.getInt("id");
                String email = rset.getString("email");
                String username = rset.getString("username");
                int score = rset.getInt("score");
                all.add(new User(id, username, email, score));
            }
            return all;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
