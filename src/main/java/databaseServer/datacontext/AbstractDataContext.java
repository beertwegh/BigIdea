package databaseServer.datacontext;

import java.sql.*;

public abstract class AbstractDataContext  {
    protected static String connString = "jdbc:mysql://94.211.149.51:171/Toohak?user=bas&password=roggel98";

    protected static String queryString;

    protected static Connection connection;

}
