package modelsTest;

import models.User;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import utilities.Hashing;

public class UserTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private String username = "username";
    private String email = "email";
    private String password = "password";
    private User user;

    //region Constructor1
    @Test
    public void testConstructor1() {
        user = new User(1, username, email, 0, password);
        Assert.assertEquals(1, user.getId());
        Assert.assertEquals(username, user.getUsername());
        Assert.assertEquals(email, user.getEmail());
        Assert.assertEquals(password, user.getPassword());
        Assert.assertEquals(0, user.getScore());
    }

    @Test
    public void testConstructor1UsernameNull() {
        exception.expect(IllegalArgumentException.class);
        user = new User(1, null, email, 0, password);
    }
    @Test
    public void testConstructor1EmailNull() {
        exception.expect(IllegalArgumentException.class);
        user = new User(1, username, null, 0, password);
    }
    @Test
    public void testConstructor1ScoreNull() {
        user = new User(1, username, email, null, password);
        Assert.assertEquals(0, user.getScore());
    }
    @Test
    public void testConstructor1PasswordNull() {
        exception.expect(IllegalArgumentException.class);
        user = new User(1, username, email, 0, null);
    }
//endregion
    //region Constructor2
    @Test
    public void testConstructor2() {
        user = new User(1, username, email, 1);
        Assert.assertEquals(1, user.getId());
        Assert.assertEquals(username, user.getUsername());
        Assert.assertEquals(email, user.getEmail());
        Assert.assertEquals(1, user.getScore());
    }
    //no null tests needed as nulltests are handled in setter
    //endregion

    //region Constructor3
    @Test
    public void testConstructor3() {
        user = new User(username, email, password);
        Assert.assertEquals(username, user.getUsername());
        Assert.assertEquals(email, user.getEmail());
        Assert.assertEquals(Hashing.getSha512Securepassword(password, "salty"), user.getPassword());
    }
    //endregion
}
