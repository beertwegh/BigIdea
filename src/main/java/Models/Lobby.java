package Models;


public class Lobby {

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


    public Lobby(String ip, String name) {
        this.ip = ip;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}