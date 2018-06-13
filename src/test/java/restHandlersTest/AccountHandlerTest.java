package restHandlersTest;

import databaseServer.datacontext.CredentialsDataContext;
import databaseServer.repositories.UserRepository;
import databaseServer.rest.handlers.AccountHandler;
import databaseServer.rest.handlers.IAccountHandler;
import databaseServer.rest.response.Reply;
import databaseServer.rest.response.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.xml.sax.SAXException;
import shared.restrequest.Login;
import shared.restrequest.Register;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class AccountHandlerTest {
    private IAccountHandler handler;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        handler = new AccountHandler(new UserRepository(new CredentialsDataContext()));
    }

    @Test
    public void testRegister() {
        Reply reply = handler.register(new Register("test@gmail.com", "usertest", "passtest"));
        Assert.assertEquals(Status.OK, reply.getStatus());
    }

    @Test
    public void testRegisterNullEmail() {
        exception.expect(IllegalArgumentException.class);
        handler.register(new Register(null, "usertest", "passtest"));
    }

    @Test
    public void testRegisterNullUsername() {
        exception.expect(IllegalArgumentException.class);
        handler.register(new Register("test@gmail.com", null, "passtest"));
    }

    @Test
    public void testRegisterNullPassword() {
        exception.expect(IllegalArgumentException.class);
        handler.register(new Register("test@gmail.com", "usertest", null));
    }

    @Test
    public void testLogin() {
        Reply reply = handler.login(new Login("usertest", "passtest"));
        Assert.assertEquals(Status.OK, reply.getStatus());
    }

    @Test
    public void testLoginNullUserEmail() {
        exception.expect(IllegalArgumentException.class);
        handler.login(new Login(null, "passtest"));
    }

    @Test
    public void testLoginNullPass() {
        exception.expect(IllegalArgumentException.class);
        handler.login(new Login("usertest", null));
    }
}