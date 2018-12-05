package org.lukebillington.university.sharesbroker.controllers;

import org.lukebillington.university.sharesbroker.data.ICompanySharesRepository;
import org.lukebillington.university.sharesbroker.data.models.CompanyShare;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Shares controller - allows access and management of shares
 */
@Path("shares")
@Consumes(MediaType.APPLICATION_JSON)
public class SharesController {
    @Inject
    ICompanySharesRepository companySharesRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllShares() {
        List<CompanyShare> sharesToReturn = companySharesRepository.getShares();
        return Response.ok(sharesToReturn).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateShares() {
        // todo: update shares based on purchase
        return Response.serverError().build();
    }
}
