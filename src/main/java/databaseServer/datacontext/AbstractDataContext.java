package databaseServer.datacontext;

import java.sql.*;

abstract class AbstractDataContext {
    static String connString = "jdbc:mysql://94.211.149.51:171/Toohak?user=bas&password=roggel98";
    static String queryString;
    static Connection connection;

    public AbstractDataContext() {
    }

    public AbstractDataContext(String differentConnString) {
        connString = differentConnString;
    }
}