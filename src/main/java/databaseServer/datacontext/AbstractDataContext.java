package databaseServer.datacontext;

import com.google.gson.Gson;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.json.Json;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public abstract class AbstractDataContext {
    protected static String connString = "jdbc:mysql://94.211.149.51:171/Toohak?user=bas&password=roggel98";
    protected static String queryString;

    protected static Connection connection;


}

