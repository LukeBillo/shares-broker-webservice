package org.lukebillington.university.sharesbroker.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(UserAdminController.Path)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserAdminController {
    public static final String Path = "user";

    @POST
    public Response CreateUser() {
        return Response.serverError().build();
    }
}
