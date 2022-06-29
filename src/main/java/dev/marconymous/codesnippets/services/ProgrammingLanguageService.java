package dev.marconymous.codesnippets.services;

import dev.marconymous.codesnippets.annotations.UUIDValidNotNull;
import dev.marconymous.codesnippets.data.DataHandler;
import dev.marconymous.codesnippets.model.ProgrammingLanguage;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import static dev.marconymous.codesnippets.Utils.Roles.ADMIN;
import static dev.marconymous.codesnippets.Utils.Roles.USER;
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
  public Response getAll(
    @CookieParam("token") String token
  ) {
    validateLogin(token, USER);
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
  public Response getSingle(@QueryParam("uuid") String uuid,
                            @CookieParam("token") String token) {
    validateLogin(token, USER);

    return generateResponseForGET(DataHandler.getLanguageByUUID(uuid), uuid);
  }

  @POST
  @Produces(MediaType.TEXT_PLAIN)
  public Response create(
    @Valid @BeanParam ProgrammingLanguage language,
    @CookieParam("token") String token
  ) {
    validateLogin(token, ADMIN);

    DataHandler.addLanguage(language);
    return Response.ok().entity("Language Created").build();
  }

  @DELETE
  @Produces(MediaType.TEXT_PLAIN)
  @Override
  public Response delete(
    @QueryParam("uuid") String uuid,
    @CookieParam("token") String token
  ) {
    validateLogin(token, ADMIN);

    if(anyIsNull(uuid))
      return Response.status(Response.Status.BAD_REQUEST).entity("uuid is not defined").build();

    DataHandler.removeLanguage(DataHandler.getLanguageByUUID(uuid));
    return Response.ok().entity("Language deleted!").build();
  }

  @PUT
  @Produces(MediaType.TEXT_PLAIN)
  public Response update(
    @Valid @BeanParam ProgrammingLanguage language,
    @CookieParam("token") String token
  ) {
    validateLogin(token, ADMIN);


    if (!new UUIDValidNotNull.Validator().isValid(language.getUUID(), null))
      return Response.status(Response.Status.BAD_REQUEST).entity("uuid is not defined/not valid").build();

    System.out.println("Updating language " + language.getUUID());
    var pl = DataHandler.getLanguageByUUID(language.getUUID());

    copyAttributes(language, pl);

    DataHandler.saveLanguageFile();

    return Response.ok().entity("Language updated!").build();
  }

  private void copyAttributes(ProgrammingLanguage copyFrom, ProgrammingLanguage copyTo) {
    copyTo.setDescription(copyFrom.getDescription());
    copyTo.setName(copyFrom.getName());
    copyTo.setImageUrl(copyFrom.getImageUrl());
  }
}
