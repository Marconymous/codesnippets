package dev.marconymous.codesnippets.services;

import dev.marconymous.codesnippets.Utils;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.util.Arrays;
import java.util.Map;

import static jakarta.ws.rs.core.Response.Status.UNAUTHORIZED;

/**
 * @author Marc Andri Fuchs
 * <p>
 * Filter to check if user is logged in.
 */
@Provider
public class AuthorizationFilter implements ContainerRequestFilter {
  private static final String USER_COOKIE_TOKEN = "token";

  @Context
  private ResourceInfo resourceInfo;

  /**
   * Uses Java reflection to check if the users' role is allowed to access the resource.
   *
   * @param requestContext request context.
   * @see PermitAll
   * @see RolesAllowed
   * @see DenyAll
   */
  @Override
  public void filter(ContainerRequestContext requestContext) {
    var tokenCookie = requestContext.getCookies().entrySet().stream()
      .filter(entry -> entry.getKey().equals(USER_COOKIE_TOKEN)).findFirst()
      .map(Map.Entry::getValue).orElse(null);

    var called = resourceInfo.getResourceMethod();

    if (called.isAnnotationPresent(DenyAll.class)) {
      requestContext.abortWith(abortResponse());
      return;
    }

    if (called.isAnnotationPresent(PermitAll.class)) {
      return;
    }

    if (tokenCookie == null) {
      requestContext.abortWith(abortResponse());
      return;
    }

    var loggedIn = Utils.AES256.decrypt(tokenCookie.getValue()).split(";")[1];

    if (called.isAnnotationPresent(RolesAllowed.class)) {
      var roles = called.getAnnotation(RolesAllowed.class).value();
      if (Arrays.stream(roles).noneMatch(r -> r.equals(loggedIn))) {
        requestContext.abortWith(abortResponse());
      }
      return;
    }

    requestContext.abortWith(Response.status(UNAUTHORIZED).entity("Requested resource doesn't have any access restrictions.").build());
  }

  private Response abortResponse() {
    return Response.status(UNAUTHORIZED).build();
  }
}
