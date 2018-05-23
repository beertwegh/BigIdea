package DatabaseServer.DataContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractDataContext {
    private static String connString = "jdbc:mysql://94.211.149.51//Toohak?user=bas&password=roggel98";

    public static void findAll() {
        try {
            Connection connection = DriverManager.getConnection(connString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static public void main(String args[]) {
        findAll();
    }
}
