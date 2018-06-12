package databaseServer.datacontext;

import jdk.internal.org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.sql.*;

public abstract class AbstractDataContext {
    protected static String connString = "jdbc:mysql://94.211.149.51:171/Toohak?user=bas&password=";
    protected static String queryString;

    protected static Connection connection;

    public AbstractDataContext() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("src/main/resources/credential.xml"));
        Element rootElement = document.getDocumentElement();
        connString = connString + rootElement.getAttribute("password");
    }

}
