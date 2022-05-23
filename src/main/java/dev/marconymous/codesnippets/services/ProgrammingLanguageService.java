package dev.marconymous.codesnippets.services;

import dev.marconymous.codesnippets.data.DataHandler;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Service for programming languages.
 */
@Path("/language")
public class ProgrammingLanguageService extends CRUDService {
  /**
   * Lists all programming languages.
   *
   * @return Response with all programming languages.
   */
  @Override
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/all")
  public Response getAll() {
    return generateResponseForGET(DataHandler.getLanguageList());
  }

  /**
   * Lists all programming languages.
   *
   * @param uuid UUID of item.
   * @return list of programming languages.
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Override
  public Response getSingle(@QueryParam("uuid") String uuid) {
    return generateResponseForGET(DataHandler.getLanguageByUUID(uuid), uuid);
  }
}
