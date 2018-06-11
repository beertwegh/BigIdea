package shared.restrequest;


import Utilities.Hashing;

public class Login {
    private String useremail;
    private String password;

    public Login(String useremail, String password) {
        this.useremail = useremail;

        this.password = Hashing.get_SHA_512_SecurePassword(password, "salty");
    }

    public String getUseremail() {
        return useremail;
    }

    public String getPassword() {
        return password;
    }
}
