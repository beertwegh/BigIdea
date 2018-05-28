package databaseServer.rest;

import databaseServer.datacontext.CredentialsDataContext;
import databaseServer.datacontext.HighScoreDataContext;
import databaseServer.datacontext.QuestionDataContext;
import databaseServer.repositories.HighScoreRepository;
import databaseServer.repositories.QuestionRepository;
import databaseServer.repositories.UserRepository;
import databaseServer.rest.handlers.*;
import databaseServer.rest.services.AccountService;
import databaseServer.rest.services.HighScoreService;
import databaseServer.rest.services.LobbyService;
import databaseServer.rest.services.QuestionService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

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
        IHighScoreHandler highScoreHandler = new HighScoreHandler(new HighScoreRepository(new HighScoreDataContext()));
        HighScoreService.setHandler(highScoreHandler);
        IQuestionHandler questionHandler = new QuestionHandler((new QuestionRepository(new QuestionDataContext())));
        QuestionService.setHandler(questionHandler);
        // Tells the Jersey Servlet which REST service/class to load.
        String services = HighScoreService.class.getCanonicalName() + "," +
                AccountService.class.getCanonicalName() + "," + QuestionService.class.getCanonicalName() + "," + LobbyService.class.getCanonicalName();

        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", services);
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
