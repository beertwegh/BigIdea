package Models;

import java.util.ArrayList;

public class Lobby {

    private ArrayList<User> players;
    private User host;
    private int id;
    private String ip;
    private String name;

    public Lobby(int id, String ip, String name) {
        this.id = id;
        this.ip = ip;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    /**
     * @param host
     */
    public Lobby(User host) {
        // TODO - implement Models.Lobby.Models.Lobby
        throw new UnsupportedOperationException();
    }

    /**
     * @param user
     */
    public void addUser(User user) {
        // TODO - implement Models.Lobby.addUser
        throw new UnsupportedOperationException();
    }

}