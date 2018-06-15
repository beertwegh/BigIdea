package models;


public class Lobby {

    private int id;
    private String ip;
    private String name;

    public Lobby(int id, String ip, String name) {
        if (ip == null || name == null) {
            throw new IllegalArgumentException("all fields have to be filled");
        } else {
            this.id = id;
            this.ip = ip;
            this.name = name;
        }
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
        if (ip == null || name == null) {
            throw new IllegalArgumentException("all fields have to be filled");
        } else {
            this.ip = ip;
            this.name = name;
        }
    }

    @Override
    public String toString() {
        return name;
    }

}