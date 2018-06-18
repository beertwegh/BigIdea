package testhandlersrest;

import databaseserver.datacontext.CredentialsDataContext;
import databaseserver.repositories.UserRepository;
import databaseserver.rest.handlers.AccountHandler;
import databaseserver.rest.handlers.IAccountHandler;
import databaseserver.rest.response.Reply;
import databaseserver.rest.response.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import shared.restrequest.Login;
import shared.restrequest.Register;

public class AccountHandlerTest extends DbCleaner {
    private IAccountHandler handler;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        handler = new AccountHandler(new UserRepository(new CredentialsDataContext(testConnString)));
        emptyTable("Credentials");
    }

    @Test
    public void testRegister() {
        Reply reply = handler.register(new Register("test", "username", "passtest"));
        Assert.assertEquals(Status.OK, reply.getStatus());
    }

    @Test
    public void testRegisterEmailNull() {
        exception.expect(IllegalArgumentException.class);
        handler.register(new Register(null, "username", "passtest"));
    }

    @Test
    public void testRegisterUsernameNull() {
        exception.expect(IllegalArgumentException.class);
        handler.register(new Register("test", null, "passtest"));
    }

    @Test
    public void testRegisterPasswordNull() {
        exception.expect(IllegalArgumentException.class);
        handler.register(new Register("test", "username", null));
    }

    @Test
    public void testLogin() {
        //register account first
        handler.register(new Register("test", "username", "passtest"));

        Reply reply = handler.login(new Login("username", "passtest"));
        Assert.assertEquals(Status.OK, reply.getStatus());
    }

    @Test
    public void testLoginNonExistent() {
        Reply reply = handler.login(new Login("username", "passtest"));
        Assert.assertEquals(Status.NOTFOUND, reply.getStatus());
    }

    @Test
    public void testLoginIncorrect() {

        //register account first
        handler.register(new Register("test", "username", "passtest"));

        Reply reply = handler.login(new Login("username", "passtest1"));
        Assert.assertEquals(Status.NOACCESS, reply.getStatus());
    }

    @Test
    public void testLoginNullUserEmail() {
        //register account first
        handler.register(new Register("test", "username", "passtest"));
        exception.expect(IllegalArgumentException.class);
        handler.login(new Login(null, "passtest"));
    }

    @Test
    public void testLoginNullPassword() {
        //register account first
        handler.register(new Register("test", "username", "passtest"));
        exception.expect(IllegalArgumentException.class);
        handler.login(new Login("username", null));
    }
}
