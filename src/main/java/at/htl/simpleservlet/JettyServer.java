package at.htl.simpleservlet;

import at.htl.simpleservlet.boundary.BlockingServlet;
import at.htl.simpleservlet.boundary.DatabaseServlet;
import at.htl.simpleservlet.boundary.SimpleServlet;
import at.htl.simpleservlet.controller.Database;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

public class JettyServer {

    private Server server;

    void start() throws Exception {
        server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8081);
        server.setConnectors(new Connector[]{connector});

        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);

        servletHandler.addServletWithMapping(SimpleServlet.class, "/");
        servletHandler.addServletWithMapping(BlockingServlet.class, "/status");
        servletHandler.addServletWithMapping(DatabaseServlet.class, "/api/person");

        server.start();
        Database.createTablePerson();
    }

    void stop() throws Exception {
        server.stop();
    }

    public static void main(String[] args) throws Exception {
        JettyServer jettyServer = new JettyServer();
        jettyServer.start();
    }
}
