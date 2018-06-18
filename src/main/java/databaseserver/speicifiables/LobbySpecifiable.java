package databaseserver.speicifiables;

public class LobbySpecifiable implements Specifiable {

    private String specifibale;
    private String parameter;


    @Override
    public String getSpecifiable() {
        return specifibale;
    }

    @Override
    public String getParameter() {
        return parameter;
    }

    public LobbySpecifiable(String specifibale, String parameter) {
        this.specifibale = specifibale;
        this.parameter = parameter;
    }

    public static LobbySpecifiable getByIp(String ip) {
        return new LobbySpecifiable("ip", ip);
    }
}
