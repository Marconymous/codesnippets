package dev.marconymous.codesnippets.services;

import dev.marconymous.codesnippets.annotations.UUIDValidNotNull;
import dev.marconymous.codesnippets.data.DataHandler;
import dev.marconymous.codesnippets.model.Tag;
import jakarta.decorator.Delegate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.xml.crypto.Data;
import java.util.UUID;

import static dev.marconymous.codesnippets.Utils.UUIDString;

/**
 * Tag service.
 */
@Path("/tag")
public class TagService extends CRUDService<Tag> {
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
    return generateResponseForGET(data, uuid);
  }


  /**
   * Create a tag
   *
   * @param tag the tag to create
   * @return Status code 200 if successful
   */
  @POST
  @Produces(MediaType.TEXT_PLAIN)
  public Response create(
    @Valid @BeanParam Tag tag
  ) {
    tag.setUUID(UUIDString());
    DataHandler.addTag(tag);

    return Response.ok().entity("Tag created").build();
  }

  /**
   * Update a Tag by id
   *
   * @param tag the tag to update
   * @return Status code 200 if successful
   */
  @PUT
  @Produces(MediaType.TEXT_PLAIN)
  public Response update(
    @Valid @BeanParam Tag tag
  ) {
    if (!new UUIDValidNotNull.Validator().isValid(tag.getUUID(), null))
      return Response.status(Response.Status.BAD_REQUEST).entity("uuid is not defined/not valid").build();

    var old = DataHandler.getTagByUUID(tag.getUUID());
    old.setName(tag.getName());

    DataHandler.saveTagFile();

    return Response.ok().entity("Tag updated").build();
  }

  /**
   * Delete a Tag by id
   *
   * @param uuid the uuid of the tag to delete
   * @return Status code 200 if successful
   */
  @DELETE
  @Produces(MediaType.TEXT_PLAIN)
  public Response delete(
    @UUIDValidNotNull @QueryParam("uuid") String uuid
  ) {
    var tag = DataHandler.getTagByUUID(uuid);
    DataHandler.removeTag(tag);

    return Response.ok().entity("Tag deleted").build();
  }
}
