package dev.marconymous.codesnippets.services;

import dev.marconymous.codesnippets.annotations.UUIDValidNotNull;
import dev.marconymous.codesnippets.data.DataHandler;
import dev.marconymous.codesnippets.model.CodeSnippet;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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

  /**
   * Creates a new code snippet.
   *
   * @param obj Object to create.
   * @return Response status code 200 if successful.
   */
  @Override
  @POST
  @Produces(MediaType.TEXT_PLAIN)
  public Response create(
    @Valid @BeanParam CodeSnippet obj
  ) {
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
  @Override
  public Response update(
    @Valid @BeanParam CodeSnippet obj
  ) {
    var old = DataHandler.getCodeSnippetByUUID(obj.getUUID());

    copyAttributes(obj, old);
    return null;
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
  public Response delete(@UUIDValidNotNull String uuid) {
    var data = DataHandler.getCodeSnippetByUUID(uuid);
    DataHandler.removeCodeSnippet(data);

    return Response.ok().entity("CodeSnippet deleted").build();
  }
}
