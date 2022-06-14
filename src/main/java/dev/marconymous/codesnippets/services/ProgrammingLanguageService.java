package dev.marconymous.codesnippets.services;

import dev.marconymous.codesnippets.data.DataHandler;
import dev.marconymous.codesnippets.model.ProgrammingLanguage;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import static dev.marconymous.codesnippets.Utils.anyIsNull;

/**
 * Service for programming languages.
 */
@Path("/language")
public class ProgrammingLanguageService extends CRUDService<ProgrammingLanguage> {
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
    @Valid @BeanParam ProgrammingLanguage language
  ) {
    DataHandler.addLanguage(language);
    return Response.ok().entity("").build();
  }

  @DELETE
  @Produces(MediaType.TEXT_PLAIN)
  @Override
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
    @Valid @BeanParam ProgrammingLanguage language
  ) {
    var pl = DataHandler.getLanguageByUUID(language.getUUID());

    copyAttributes(language, pl);

    DataHandler.saveLanguageFile();

    return Response.ok().entity("").build();
  }

  private void copyAttributes(ProgrammingLanguage copyFrom, ProgrammingLanguage copyTo) {
    copyTo.setDescription(copyFrom.getDescription());
    copyTo.setName(copyFrom.getName());
    copyTo.setImageUrl(copyFrom.getImageUrl());
  }
}
