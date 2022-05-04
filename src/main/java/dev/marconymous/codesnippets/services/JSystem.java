package dev.marconymous.codesnippets.services;

import jakarta.ws.rs.Path;

@Path("/system")
public class System {
  @Path("/ping")
  public String ping() {
    return "pong at " + java.lang.System.currentTimeMillis();
  }
}
