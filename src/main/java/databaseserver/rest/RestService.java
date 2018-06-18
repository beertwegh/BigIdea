package databaseserver.rest;

import databaseserver.datacontext.CredentialsDataContext;
import databaseserver.datacontext.HighScoreDataContext;
import databaseserver.datacontext.LobbyDataContext;
import databaseserver.datacontext.QuestionDataContext;
import databaseserver.repositories.HighScoreRepository;
import databaseserver.repositories.LobbyRepository;
import databaseserver.repositories.QuestionRepository;
import databaseserver.repositories.UserRepository;
import databaseserver.rest.handlers.*;
import databaseserver.rest.services.AccountService;
import databaseserver.rest.services.HighScoreService;
import databaseserver.rest.services.LobbyService;
import databaseserver.rest.services.QuestionService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import shared.logging.Logger;

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
        ILobbyHandler lobbyHandler = new LobbyHandler(new LobbyRepository(new LobbyDataContext()));
        LobbyService.setHandler(lobbyHandler);
        // Tells the Jersey Servlet which REST service/class to load.
        String services = HighScoreService.class.getCanonicalName() + "," +
                AccountService.class.getCanonicalName() + "," + QuestionService.class.getCanonicalName() + "," + LobbyService.class.getCanonicalName();

        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", services);
        try {
            jettyServer.start();
            jettyServer.join();
        } catch (Exception e) {
            Logger.getInstance().log(e);
        } finally {
            jettyServer.destroy();
        }
    }
}
