package DatabaseServer.rest;

import DatabaseServer.datacontext.CredentialsDataContext;
import DatabaseServer.repositories.UserRepository;
import DatabaseServer.rest.handlers.AccountHandler;
import DatabaseServer.rest.handlers.IAccountHandler;
import DatabaseServer.rest.services.AccountService;
import DatabaseServer.rest.services.HighScoreService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import java.util.HashMap;
import java.util.Map;

public class RestService {

    public static void main(String[] args) {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        Server jettyServer = new Server(8090);
        jettyServer.setHandler(context);
        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);
        IAccountHandler accountHandler = new AccountHandler(new UserRepository(new CredentialsDataContext()));
        AccountService.setHandler(accountHandler);

        // Tells the Jersey Servlet which REST service/class to load.
        Map<String, String> services = new HashMap<>();
        services.put("jersey.config.server.provider.classnames",
                AccountService.class.getCanonicalName());
        services.put("jersey.config.server.provider.classnames", HighScoreService.class.getCanonicalName());
        jerseyServlet.setInitParameters(services);
        try {
            jettyServer.start();
            jettyServer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jettyServer.destroy();
        }
    }
}
