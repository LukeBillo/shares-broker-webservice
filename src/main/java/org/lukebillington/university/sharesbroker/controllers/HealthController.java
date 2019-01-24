package org.lukebillington.university.sharesbroker.controllers;

import com.mongodb.MongoClient;
import org.lukebillington.university.sharesbroker.data.mongo.IMongoConnectionManager;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(HealthController.Path)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HealthController {
    private MongoClient _mongoClient;
    public static final String Path = "health";

    @Inject
    public HealthController(IMongoConnectionManager mongoConnectionManager) {
        _mongoClient = mongoConnectionManager.getClient();
    }

    /**
     * Health check endpoint that simply attempts to connect to the database.
     * @return OK response, assuming database connection successful.
     */
    @GET
    public Response HealthCheck() {
        _mongoClient.getDatabase("Shares").getCollection("Shares");
        return Response.ok().build();
    }
}
