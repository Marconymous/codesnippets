package dev.marconymous.codesnippets.services;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

import static dev.marconymous.codesnippets.Config.ALLOWED_ORIGINS;


/**
 * Filter to add CORS headers to responses.
 */
@Provider
@SuppressWarnings("unused")
public class CorsFilter implements ContainerResponseFilter {
  /**
   * Allow CORS from any origin.
   *
   * @param requestContext request context.
   * @param responseContext response context.
   */
  @Override
  public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {

    responseContext.getHeaders().add("Access-Control-Allow-Origin", ALLOWED_ORIGINS);
    responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
    responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
    responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
  }
}


