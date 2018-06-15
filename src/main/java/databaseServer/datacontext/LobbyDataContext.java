package databaseServer.datacontext;

import databaseServer.speicifiables.Specifiable;
import models.Lobby;
import shared.Logging.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LobbyDataContext extends AbstractDataContext implements ILobbyDataContext {
    public LobbyDataContext() {
    }

    public LobbyDataContext(String differentConnString) {
        super(differentConnString);
    }

    @Override
    public Lobby findOne(Specifiable specifiable) {
        try {
            connection = DriverManager.getConnection(connString);
            queryString = "SELECT id, name, ip FROM Lobby WHERE " + specifiable.getSpecifiable() + " = ?";
            PreparedStatement stmt = connection.prepareStatement(queryString);
            stmt.setString(1, specifiable.getParameter());
            try (ResultSet rset = stmt.executeQuery()) {
                if (!rset.next()) {
                    return null;
                } else {
                    rset.first();
                    Lobby lobby = new Lobby(rset.getInt("id"), rset.getString("ip"), rset.getString("name"));
                    connection.close();
                    rset.close();
                    return lobby;
                }
            }
        } catch (SQLException e) {
            Logger.getInstance().log(e);
        }
        return null;
    }


    @Override
    public void save(Lobby item) {
        try {
            connection = DriverManager.getConnection(connString);
            queryString = "INSERT INTO Lobby (name, ip) VALUES(?, ?)";
            PreparedStatement stmt = connection.prepareStatement(queryString);
            stmt.setString(1, item.getName());
            stmt.setString(2, item.getIp());
            stmt.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            Logger.getInstance().log(e);
        }
    }


    @Override
    public List<Lobby> findAll() {
        List<Lobby> all = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(connString);
            queryString = "SELECT id, name, ip FROM Lobby";
            Statement stmt = connection.createStatement();
            try (ResultSet rset = stmt.executeQuery(queryString)) {
                while (rset.next()) {
                    int id = rset.getInt("id");
                    String name = rset.getString("name");
                    String ip = rset.getString("ip");
                    all.add(new Lobby(id, ip, name));
                }
                connection.close();
                rset.close();
                return all;
            }
        } catch (SQLException e) {
            Logger.getInstance().log(e);
        }
        return all;
    }

    @Override
    public void clearLobbies() {
        try {
            connection = DriverManager.getConnection(connString);
            queryString = "DELETE FROM Lobby";
            PreparedStatement stmt = connection.prepareStatement(queryString);
            stmt.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            Logger.getInstance().log(e);
        }
    }
}
