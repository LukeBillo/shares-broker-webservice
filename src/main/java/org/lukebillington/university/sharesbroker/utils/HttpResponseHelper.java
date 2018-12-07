package org.lukebillington.university.sharesbroker.utils;

import javax.ws.rs.core.Response;

public abstract class HttpResponseHelper {
    public static Response CreateBadRequest(String message) {
        return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), message).build();
    }
}
