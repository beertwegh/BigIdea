package testhandlersrest;

import databaseServer.datacontext.CredentialsDataContext;
import databaseServer.repositories.UserRepository;
import databaseServer.rest.handlers.AccountHandler;
import databaseServer.rest.handlers.IAccountHandler;
import databaseServer.rest.response.Reply;
import databaseServer.rest.response.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import shared.restrequest.Register;

public class AccountHandlerTest {
    private IAccountHandler handler;

    @Before
    public void init() {
        handler = new AccountHandler(new UserRepository(new CredentialsDataContext("test")));
    }

    @Test
    public void testRegister() {
        Reply reply = handler.register(new Register("test", "username", "passtest"));
        Assert.assertEquals(Status.OK, reply.getStatus());
    }
}
