package at.htl.simpleservlet;

import at.htl.simpleservlet.boundary.BlockingServlet;
import at.htl.simpleservlet.boundary.SimpleServlet;
import jakarta.servlet.Servlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

import java.io.IOException;
import java.net.ContentHandler;
import java.net.URLConnection;

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

        server.start();
    }

    void stop() throws Exception {
        server.stop();
    }

    public static void main(String[] args) throws Exception {
        JettyServer jettyServer = new JettyServer();
        jettyServer.start();
    }
}
