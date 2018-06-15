package modelsTest;

import models.Lobby;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LobbyTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testConstructor() {
        Lobby lobby = new Lobby(1, "0.0.0.0:1234", "name");
        Assert.assertEquals(1, lobby.getId());
        Assert.assertEquals("0.0.0.0:1234", lobby.getIp());
        Assert.assertEquals("name", lobby.getName());
    }

    @Test
    public void testConstructor1IpNull() {
        exception.expect(IllegalArgumentException.class);
        Lobby lobby = new Lobby(0, null, "name");
    }

    @Test
    public void testConstructor2IpNull() {
        exception.expect(IllegalArgumentException.class);
        Lobby lobby = new Lobby(null, "name");
    }

    @Test
    public void testConstructor1NameNull() {
        exception.expect(IllegalArgumentException.class);
        Lobby lobby = new Lobby(0, "0.0.0.0:1234", null);
    }

    @Test
    public void testConstructor2NameNull() {
        exception.expect(IllegalArgumentException.class);
        Lobby lobby = new Lobby("0.0.0.0:1234", null);
    }

    @Test
    public void testToString() {
        Lobby lobby = new Lobby("0.0.0.0:1234", "name");
        Assert.assertEquals("name", lobby.toString());
    }
}
