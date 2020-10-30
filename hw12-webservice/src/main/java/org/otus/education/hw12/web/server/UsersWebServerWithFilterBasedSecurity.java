package org.otus.education.hw12.web.server;

import com.google.gson.Gson;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.otus.education.hw12.data.core.service.DBServiceUser;
import org.otus.education.hw12.web.services.TemplateProcessor;
import org.otus.education.hw12.web.services.UserAuthService;
import org.otus.education.hw12.web.servlet.AuthorizationFilter;
import org.otus.education.hw12.web.servlet.LoginServlet;
import org.otus.education.hw12.web.servlet.UsersApiServlet;
import org.otus.education.hw12.web.servlet.UsersServlet;

import javax.servlet.Filter;
import java.util.Arrays;

public class UsersWebServerWithFilterBasedSecurity implements UsersWebServer {

    private static final String ROLE_NAME_ADMIN = "admin";
    private static final String CONSTRAINT_NAME = "auth";

    private static final String START_PAGE_NAME = "index.html";
    private static final String COMMON_RESOURCES_DIR = UsersWebServerWithBasicSecurity.class.getResource("/static").toExternalForm();
    private static final String USERS = "/users";
    private static final String USERS_API = "/api/user/*";


    private final UserAuthService authService;
    private final Gson gson;
    private final DBServiceUser dbServiceUser;
    private final TemplateProcessor templateProcessor;
    private final Server server;

    public UsersWebServerWithFilterBasedSecurity(int port, UserAuthService authService, Gson gson, DBServiceUser dbServiceUser, TemplateProcessor templateProcessor) {
        this.authService = authService;
        this.gson = gson;
        this.dbServiceUser = dbServiceUser;
        this.templateProcessor = templateProcessor;
        server = new Server(port);
    }

    @Override
    public void start() throws Exception {
        if (server.getHandlers().length == 0) {
            initContext();
        }
        server.start();
    }

    @Override
    public void join() throws Exception {
        server.join();
    }

    @Override
    public void stop() throws Exception {
        server.stop();
    }

    private Server initContext() {
        var resourceHandler = createResourceHandler();
        var servletContextHandler = createServletContextHandler();

        var handlers = new HandlerList();
        handlers.addHandler(resourceHandler);
        handlers.addHandler(applySecurity(servletContextHandler, USERS, USERS_API));

        server.setHandler(handlers);
        return server;
    }

    private ResourceHandler createResourceHandler() {
        var resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(false);
        resourceHandler.setWelcomeFiles(new String[]{START_PAGE_NAME});
        resourceHandler.setResourceBase(COMMON_RESOURCES_DIR);
        return resourceHandler;
    }


    protected Handler applySecurity(ServletContextHandler servletContextHandler, String... paths) {
        servletContextHandler.addServlet(new ServletHolder(new LoginServlet(templateProcessor, authService)), "/login");
        Filter authorizationFilter = new AuthorizationFilter();
        Arrays.stream(paths).forEachOrdered(path -> servletContextHandler.addFilter(new FilterHolder(authorizationFilter), path, null));
        return servletContextHandler;
    }

    private ServletContextHandler createServletContextHandler() {
        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.addServlet(new ServletHolder(new UsersServlet(templateProcessor)), "/users");
        servletContextHandler.addServlet(new ServletHolder(new UsersApiServlet(dbServiceUser, gson)), USERS_API);
        return servletContextHandler;
    }
}
