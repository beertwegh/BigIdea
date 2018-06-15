package testhandlersrest;

import com.google.gson.Gson;
import databaseServer.datacontext.LobbyDataContext;
import databaseServer.repositories.LobbyRepository;
import databaseServer.rest.handlers.LobbyHandler;
import databaseServer.rest.response.GetLobbiesResponse;
import databaseServer.rest.response.Reply;
import databaseServer.rest.response.Status;
import models.Lobby;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LobbyHandlerTest extends DbCleaner {
    private LobbyHandler handler;
    private Gson gson = new Gson();
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        handler = new LobbyHandler(new LobbyRepository(new LobbyDataContext(testConnString)));
        emptyTable("Lobby");
    }

    @Test
    public void testCreateLobby() {
        Reply reply = handler.createLobby(new Lobby("0.0.0.0:1234", "name"));
        Assert.assertEquals(Status.OK, reply.getStatus());
    }

    @Test
    public void testCreateLobbyIpNull() {
        exception.expect(IllegalArgumentException.class);
        handler.createLobby(new Lobby(null, "name"));
    }

    @Test
    public void testCreateLobbyNameNull() {
        exception.expect(IllegalArgumentException.class);
        handler.createLobby(new Lobby("0.0.0.0:1234", null));
    }

    @Test
    public void testGetLobbies() {
        //first create lobbies to get
        handler.createLobby(new Lobby("0.0.0.0:1234", "name1"));
        handler.createLobby(new Lobby("0.0.0.0:1234", "name2"));
        Reply reply = handler.getLobbies();
        GetLobbiesResponse entity = gson.fromJson(reply.getMessage(), GetLobbiesResponse.class);
        Assert.assertEquals(2, entity.getLobbies().size());
        Assert.assertEquals("name1", entity.getLobbies().get(0).getName());
        Assert.assertEquals("name2", entity.getLobbies().get(1).getName());
    }

    @Test
    public void testChooseLobby() {
        Lobby lobby = new Lobby("0.0.0.0:1234", "name1");
        handler.createLobby(lobby);
        Reply reply = handler.chooseLobby(lobby);
        Assert.assertEquals(Status.OK, reply.getStatus());
    }

    @Test
    public void testChooseLobbyNonExistent() {
        handler.createLobby(new Lobby("0.0.0.0:1234", "name"));
        Reply reply = handler.chooseLobby(new Lobby("1.2.3.4:1234", "name"));
        Assert.assertEquals(Status.NOTFOUND, reply.getStatus());
    }

    @Test
    public void testClearLobbies() {
        handler.createLobby(new Lobby("0.0.0.0:1234", "name"));
        Reply reply = handler.getLobbies();
        GetLobbiesResponse entity = gson.fromJson(reply.getMessage(), GetLobbiesResponse.class);
        Assert.assertEquals(1, entity.getLobbies().size());
        handler.clearLobbies();
        reply = handler.getLobbies();
        entity = gson.fromJson(reply.getMessage(), GetLobbiesResponse.class);
        Assert.assertEquals(0, entity.getLobbies().size());

    }


}
