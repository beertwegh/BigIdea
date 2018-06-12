package shared.restrequest;

public class Register {
    private String email;
    private String username;
    private String password;

    public Register(String email, String username, String password) {
        if (email != null && username != null && password != null) {
            this.email = email;
            this.username = username;
            this.password = password;
        }
        else{
            throw new IllegalArgumentException("Please fill in all fields!");
        }
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
