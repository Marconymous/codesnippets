package dev.marconymous.codesnippets.services;

import dev.marconymous.codesnippets.data.DataHandler;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/language")
public class ProgrammingLanguageService extends CRUDService {
  @Override
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/all")
  public Response getAll() {
    return generateResponseForGET(DataHandler.getLanguageList());
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Override
  public Response getSingle(@QueryParam("uuid") String uuid) {
    return generateResponseForGET(DataHandler.getLanguageByUUID(uuid), uuid);
  }
}
