package databaseserver.datacontext;

import java.sql.Connection;

abstract class AbstractDataContext {
    String connString = "jdbc:mysql://94.211.149.51:171/Toohak?user=bas&password=roggel98";
    String queryString;
    Connection connection;

    AbstractDataContext() {
    }

    AbstractDataContext(String differentConnString) {
        connString = differentConnString;
    }
}