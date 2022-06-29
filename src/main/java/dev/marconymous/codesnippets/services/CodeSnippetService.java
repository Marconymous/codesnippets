package dev.marconymous.codesnippets.services;

import dev.marconymous.codesnippets.Utils;
import dev.marconymous.codesnippets.annotations.UUIDValidNotNull;
import dev.marconymous.codesnippets.data.DataHandler;
import dev.marconymous.codesnippets.model.CodeSnippet;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import static dev.marconymous.codesnippets.Utils.Roles.ADMIN;
import static dev.marconymous.codesnippets.Utils.Roles.USER;

/**
 * Service for code snippets.
 */
@Path("/snippet")
public class CodeSnippetService extends CRUDService<CodeSnippet> {

  /**
   * Reads all code snippets.
   *
   * @return Response with all code snippets.
   */
  @GET
  @Path("/all")
  @Override
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAll(
    @CookieParam("token") String token
  ) {
    validateLogin(token, USER);
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
  public Response getSingle(
    @QueryParam("uuid") String uuid,
    @CookieParam("token") String token
  ) {
    validateLogin(token, USER);

    var data = DataHandler.getCodeSnippetByUUID(uuid);

    return generateResponseForGET(data, uuid);
  }

  /**
   * Creates a new code snippet.
   *
   * @param obj Object to create.
   * @return Response status code 200 if successful.
   */
  @POST
  @Produces(MediaType.TEXT_PLAIN)
  public Response create(
    @Valid @BeanParam CodeSnippet obj,
      @CookieParam("token") String token
  ) {
    validateLogin(token, ADMIN);
    obj.setUUID(Utils.UUIDString());

    DataHandler.addCodeSnippet(obj);

    return Response.ok().entity("CodeSnippet created").build();
  }

  /**
   * Updates a code snippet.
   *
   * @param obj Object to update.
   * @return Response status code 200 if successful.
   */
  @PUT
  @Produces(MediaType.TEXT_PLAIN)
  public Response update(
    @Valid @BeanParam CodeSnippet obj,
    @CookieParam("token") String token
  ) {
    validateLogin(token, ADMIN);

    if (!new UUIDValidNotNull.Validator().isValid(obj.getUUID(), null)) {
      return Response.status(Response.Status.BAD_REQUEST).entity("UUID is not valid").build();
    }


    var old = DataHandler.getCodeSnippetByUUID(obj.getUUID());

    copyAttributes(obj, old);

    DataHandler.saveCodeSnippetFile();
    return Response.ok().entity("CodeSnippet updated").build();
  }

  private void copyAttributes(CodeSnippet upd, CodeSnippet old) {
    old.setContent(upd.getContent());
    old.setCreator(upd.getCreator());
    old.setCreationDate(upd.getCreationDate());
    old.setProgrammingLanguage(upd.getProgrammingLanguage());
    old.setTitle(upd.getTitle());
  }

  @DELETE
  @Produces(MediaType.TEXT_PLAIN)
  @Override
  public Response delete(@UUIDValidNotNull @QueryParam("uuid") String uuid,
                         @CookieParam("token") String token) {
    validateLogin(token, ADMIN);

    var data = DataHandler.getCodeSnippetByUUID(uuid);
    DataHandler.removeCodeSnippet(data);

    return Response.ok().entity("CodeSnippet deleted").build();
  }
}
