package DatabaseServer.rest.request;

public class Login {
    private String useremail;
    private String password;

    public Login(String useremail, String password) {
        this.useremail = useremail;
        this.password = password;
    }

    public String getUseremail() {
        return useremail;
    }

    public String getPassword() {
        return password;
    }
}
