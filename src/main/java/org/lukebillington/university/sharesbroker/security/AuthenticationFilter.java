package org.lukebillington.university.sharesbroker.security;

import org.glassfish.jersey.internal.util.Base64;
import org.lukebillington.university.sharesbroker.data.IUsersRepository;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    @Inject
    IUsersRepository usersRepository;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String authHeader = containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String decodedAuthHeader = Base64.decodeAsString(authHeader);

        String[] splitAuthHeader = decodedAuthHeader.split(":");
        String username = splitAuthHeader[0];
        String password = splitAuthHeader[1];

        String hashedPassword = usersRepository.getUserPasswordHash(username);

        boolean authenticated = MD5Authenticator.Authenticate(password, hashedPassword);

        if (!authenticated) {
            throw new UnauthorizedException();
        }
    }
}
