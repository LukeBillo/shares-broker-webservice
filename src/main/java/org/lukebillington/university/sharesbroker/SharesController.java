package org.lukebillington.university.sharesbroker;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Shares controller - allows access and management of shares
 */
@Path("shares")
@Consumes(MediaType.APPLICATION_JSON)
public class SharesController {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getShares() {
        // todo: return current shares
        return "NOT_IMPLEMENTED";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void updateShares() {
        // todo: update shares based on purchase
    }
}
