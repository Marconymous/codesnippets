package dev.marconymous.codesnippets.services;

import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * @author marconymous
 * @since 2020-05-04
 * <p>
 * Services with allround use.
 */
@Path("/system")
public class JSystem {

  /**
   * Service to test if the server is up and running.
   *
   * @return the current time as a json object.
   */
  @Path("/ping")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @PermitAll
  public String ping() {
    return "{\"time\":\"" + System.currentTimeMillis() + "\"}";
  }
}
