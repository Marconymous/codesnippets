package dev.marconymous.codesnippets.services;

import dev.marconymous.codesnippets.Utils;
import dev.marconymous.codesnippets.data.DataHandler;
import dev.marconymous.codesnippets.model.ProgrammingLanguage;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

import static dev.marconymous.codesnippets.Utils.*;

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

  @POST
  @Produces(MediaType.TEXT_PLAIN)
  public Response create(
    @FormParam("name") String name,
    @FormParam("description") String description,
    @FormParam("imageUrl") String imageUrl
  ) {
    if(anyIsNull(name, description, imageUrl))
      return Response.status(Response.Status.BAD_REQUEST).entity("At least one Argument is missing").build();


    var pl = new ProgrammingLanguage(UUID.randomUUID().toString(), name, description, imageUrl);
    DataHandler.addLanguage(pl);
    return Response.ok().entity("").build();
  }

  @DELETE
  @Produces(MediaType.TEXT_PLAIN)
  public Response delete(
    @QueryParam("uuid") String uuid
  ) {
    if(anyIsNull(uuid))
      return Response.status(Response.Status.BAD_REQUEST).entity("uuid is not defined").build();

    DataHandler.removeLanguage(DataHandler.getLanguageByUUID(uuid));
    return Response.ok().entity("").build();
  }

  @PUT
  @Produces(MediaType.TEXT_PLAIN)
  public Response update(
    @FormParam("uuid") String uuid,
    @FormParam("name") String name,
    @FormParam("description") String description,
    @FormParam("imageUrl") String imageUrl
  ) {
    var pl = DataHandler.getLanguageByUUID(uuid);

    if (allAreNull(name, description, imageUrl) || uuid == null) {
      return Response.status(Response.Status.BAD_REQUEST).entity("UUID or at lease one argument is missing").build();
    }

    executeIfNotNull(name, () -> pl.setName(name));
    executeIfNotNull(description, () -> pl.setDescription(description));
    executeIfNotNull(imageUrl, () -> pl.setImageUrl(imageUrl));

    DataHandler.saveLanguageFile();

    return Response.ok().entity("").build();
  }
}
