package shared.restrequest;


import Utilities.Hashing;
import com.sun.javafx.image.BytePixelSetter;

public class Login {
    private String useremail;
    private String password;

    public Login(String useremail, String password) {
        if (useremail != null && password != null) {
            this.useremail = useremail;
            this.password = Hashing.get_SHA_512_SecurePassword(password, "salty");
        } else {
            throw new IllegalArgumentException("Fill in all fields");
        }
    }

    public String getUseremail() {
        return useremail;
    }

    public String getPassword() {
        return password;
    }
}
