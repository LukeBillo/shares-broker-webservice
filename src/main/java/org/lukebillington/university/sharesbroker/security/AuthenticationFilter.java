package org.lukebillington.university.sharesbroker.security;

import org.glassfish.jersey.internal.util.Base64;
import org.lukebillington.university.sharesbroker.data.IUsersRepository;
import org.lukebillington.university.sharesbroker.data.models.User;
import org.lukebillington.university.sharesbroker.utils.HttpResponseHelper;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    @Inject
    IUsersRepository usersRepository;

    @Context
    ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        // gets the auth header, removes the "Basic " prefix and trims whitespace.
        String authHeader = containerRequestContext
                .getHeaderString(HttpHeaders.AUTHORIZATION)
                .substring("Basic".length())
                .trim();

        if (authHeader == null) {
            containerRequestContext.abortWith(HttpResponseHelper.CreateUnauthorizedResponse("No authentication information provided."));
            return;
        }

        // decodes basic auth header and splits
        String decodedAuthHeader = Base64.decodeAsString(authHeader);

        String[] splitAuthHeader = decodedAuthHeader.split(":", 2);
        String username = splitAuthHeader[0];
        String password = splitAuthHeader[1];

        boolean authenticated = false;

        try {
            // checks if MD5 hash matches database, and authenticates
            String hashedPassword = usersRepository.getUserPasswordHash(username);
            authenticated = MD5Authenticator.Authenticate(password, hashedPassword);
        } catch (Exception e) {
            containerRequestContext.abortWith(HttpResponseHelper.CreateForbiddenResponse("Username or password did not match."));
        }

        if (!authenticated) {
            containerRequestContext.abortWith(HttpResponseHelper.CreateForbiddenResponse("Username or password did not match."));
        }

        // beyond this point, user is authenticated.
        Method methodBeingAccessed = resourceInfo.getResourceMethod();
    }
}
