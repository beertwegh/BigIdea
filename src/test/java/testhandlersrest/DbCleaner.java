package testhandlersrest;

import shared.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class DbCleaner {
  protected String testConnString = "jdbc:mysql://94.211.149.51:171/testToohak?user=bas&password=roggel98";

    void emptyTable(String table) {
        try {
            Connection connection = DriverManager.getConnection(testConnString);
            String query = "DELETE FROM " + table;
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            Logger.getInstance().log(e);
        }
    }

}
