package databaseServer.datacontext;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HighScoreDataContext extends AbstractDataContext implements IHighScoreDataContext {

    @Override
    public void updateScore(int userId, int score) {
        try {
            Connection connection = DriverManager.getConnection(connString);
            queryString = "UPDATE HighScore SET Score = ? WHERE CredentialsId = ?";
            PreparedStatement stmt = connection.prepareStatement(queryString);
            stmt.setInt(1, score);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

