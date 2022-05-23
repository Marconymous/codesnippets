package dev.marconymous.codesnippets.services;

import dev.marconymous.codesnippets.data.DataHandler;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Tag service.
 */
@Path("/tag")
public class TagService extends CRUDService {
  /**
   * Get all tags.
   *
   * @return Response with all tags.
   */
  @Override
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/all")
  public Response getAll() {
    return generateResponseForGET(DataHandler.getTagList());
  }

  /**
   * Get tag by id.
   *
   * @param uuid UUID of item.
   * @return Response with tag.
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Override
  public Response getSingle(@QueryParam("uuid") String uuid) {
    var data = DataHandler.getTagByUUID(uuid);
    return generateResponseForGET(data);
  }
}
