package restClient.restactions;


import shared.restrequest.ClearLobbies;

public class ClearLobbiesAction extends BaseAction<ClearLobbies> {

    public String clearLobbies(ClearLobbies clearLobbies) {
        return super.baseMethod(clearLobbies, "http://rest.basvdeertwegh.nl/lobby/clear");
    }
}
