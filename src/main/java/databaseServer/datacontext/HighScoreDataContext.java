package databaseServer.datacontext;


import org.xml.sax.SAXException;
import shared.Logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HighScoreDataContext extends AbstractDataContext implements IHighScoreDataContext {

    public HighScoreDataContext(String differentConnString) {
        super(differentConnString);
    }

    public HighScoreDataContext() {
    }

    @Override
    public void updateScore(int userId, int score) {
        try {
            Connection connection = DriverManager.getConnection(connString);
            queryString = "INSERT INTO HighScore (CredentialsId, Score) VALUES (?, ?) ON DUPLICATE KEY UPDATE Score = ?";
            PreparedStatement stmt = connection.prepareStatement(queryString);
            stmt.setInt(1, userId);
            stmt.setInt(2, score);
            stmt.setInt(3, score);
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            Logger.getInstance().log(e);
        }
    }
}