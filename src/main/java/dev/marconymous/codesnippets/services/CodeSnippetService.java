package dev.marconymous.codesnippets.services;

import dev.marconymous.codesnippets.Utils;
import dev.marconymous.codesnippets.annotations.UUIDValidNotNull;
import dev.marconymous.codesnippets.annotations.UUIDValidNotNull.Validator;
import dev.marconymous.codesnippets.data.DataHandler;
import dev.marconymous.codesnippets.model.CodeSnippet;
import jakarta.annotation.security.RolesAllowed;
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
  @RolesAllowed({ADMIN, USER})
  public Response getAll(
  ) {
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
  @RolesAllowed({ADMIN, USER})
  public Response getSingle(
    @QueryParam("uuid") String uuid) {
    var data = DataHandler.getCodeSnippetByUUID(uuid);

    return generateResponseForGET(data, uuid);
  }

  /**
   * Creates a new code snippet.
   *
   * @param codeSnippet Object to create.
   * @return Response status code 200 if successful.
   */
  @POST
  @Produces(MediaType.TEXT_PLAIN)
  @RolesAllowed(ADMIN)
  public Response create(
    @Valid @BeanParam CodeSnippet codeSnippet
  ) {
    codeSnippet.setUUID(Utils.UUIDString());

    DataHandler.addCodeSnippet(codeSnippet);

    return Response.ok().entity("CodeSnippet created").build();
  }

  /**
   * Updates a code snippet.
   *
   * @param codeSnippet Object to update.
   * @return Response status code 200 if successful.
   */
  @PUT
  @Produces(MediaType.TEXT_PLAIN)
  @RolesAllowed(ADMIN)
  public Response update(
    @Valid @BeanParam CodeSnippet codeSnippet
  ) {
    if (!new Validator().isValid(codeSnippet.getUUID(), null)) {
      return Response.status(Response.Status.BAD_REQUEST).entity("UUID is not valid").build();
    }


    var old = DataHandler.getCodeSnippetByUUID(codeSnippet.getUUID());

    copyAttributes(codeSnippet, old);

    DataHandler.saveCodeSnippetFile();
    return Response.ok().entity("CodeSnippet updated").build();
  }

  private void copyAttributes(CodeSnippet copyFrom, CodeSnippet copyTo) {
    copyTo.setContent(copyFrom.getContent());
    copyTo.setCreator(copyFrom.getCreator());
    copyTo.setCreationDate(copyFrom.getCreationDate());
    copyTo.setProgrammingLanguage(copyFrom.getProgrammingLanguage());
    copyTo.setTitle(copyFrom.getTitle());
  }

  @DELETE
  @Produces(MediaType.TEXT_PLAIN)
  @Override
  @RolesAllowed(ADMIN)
  public Response delete(@UUIDValidNotNull @QueryParam("uuid") String uuid) {

    var data = DataHandler.getCodeSnippetByUUID(uuid);
    DataHandler.removeCodeSnippet(data);

    return Response.ok().entity("CodeSnippet deleted").build();
  }
}
