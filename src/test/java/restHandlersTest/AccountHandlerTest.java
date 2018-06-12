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
import shared.restrequest.Register;

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
}
