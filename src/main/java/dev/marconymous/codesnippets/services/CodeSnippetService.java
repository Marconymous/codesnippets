package dev.marconymous.codesnippets.services;

import dev.marconymous.codesnippets.data.DataHandler;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Service for code snippets.
 */
@Path("/snippet")
public class CodeSnippetService extends CRUDService {

  /**
   * Reads all code snippets.
   *
   * @return Response with all code snippets.
   */
  @GET
  @Path("/all")
  @Override
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAll() {
    return generateResponseForGET(DataHandler.getCodeSnippetList());
  }

  /**
   * Reads a code snippet by id.
   *
   * @param uuid UUID of the code snippet.
   * @return Response with the code snippet.
   */
  @Override
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getSingle(@QueryParam("uuid") String uuid) {
    var data = DataHandler.getCodeSnippetByUUID(uuid);

    return generateResponseForGET(data, uuid);
  }
}
