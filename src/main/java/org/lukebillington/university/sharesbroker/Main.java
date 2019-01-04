package org.lukebillington.university.sharesbroker;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BaseUri = "http://localhost:8080/";

    /**
     * Starts Grizzly HTTP server, exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        final ResourceConfig config = new AppConfig();
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BaseUri), config);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started at %s", BaseUri));
        System.out.println("Press any key to stop server.");
        System.in.read();
        server.shutdownNow();
    }
}

