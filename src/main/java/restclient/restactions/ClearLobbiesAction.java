package restclient.restactions;


import shared.restrequest.ClearLobbies;

public class ClearLobbiesAction implements BaseAction<ClearLobbies> {

    public String clearLobbies(ClearLobbies clearLobbies) {
        return baseMethod(clearLobbies, "http://rest.basvdeertwegh.nl/lobby/clear");
    }
}
