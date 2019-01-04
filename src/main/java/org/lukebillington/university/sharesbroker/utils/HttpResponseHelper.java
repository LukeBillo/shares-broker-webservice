package org.lukebillington.university.sharesbroker.utils;

import javax.ws.rs.core.Response;

public abstract class HttpResponseHelper {
    public static Response CreateBadRequestResponse(String message) {
        return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), message).build();
    }

    public static Response CreateConflictResponse(String message) {
        return Response.status(Response.Status.CONFLICT.getStatusCode(), message).build();
    }

    public static Response CreateForbiddenResponse(String message) {
        return Response.status(Response.Status.FORBIDDEN.getStatusCode(), message).build();
    }

    public static Response CreateUnauthorizedResponse(String message) {
        return Response.status(Response.Status.UNAUTHORIZED.getStatusCode(), message).build();
    }

    public static Response CreateNotFoundResponse() {
        return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
    }
}
