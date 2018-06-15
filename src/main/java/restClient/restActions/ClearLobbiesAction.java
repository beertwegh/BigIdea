package restClient.restActions;


import shared.restrequest.ClearLobbies;

public class ClearLobbiesAction extends BaseAction<ClearLobbies> {

    public void clearLobbies(ClearLobbies clearLobbies) {
        super.baseMethod(clearLobbies, "http://rest.basvdeertwegh.nl/lobby/clear");
    }
}
