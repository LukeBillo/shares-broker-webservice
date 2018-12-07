package org.lukebillington.university.sharesbroker.controllers;

import org.lukebillington.university.sharesbroker.data.ICompanySharesRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("shares/admin")
@Consumes(MediaType.APPLICATION_JSON)
public class SharesAdminController {
    @Inject
    ICompanySharesRepository companySharesRepository;

    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    public Response patchUpdateShare() {
        // todo: update shares admin functionality
        return Response.serverError().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response postNewShare() {
        return Response.serverError().build();
    }
}
