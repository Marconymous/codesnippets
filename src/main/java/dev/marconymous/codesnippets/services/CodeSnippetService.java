package dev.marconymous.codesnippets.services;

import dev.marconymous.codesnippets.data.DataHandler;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/snippet")
public class CodeSnippetService extends CRUDService {
  @GET
  @Path("/all")
  @Override
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAll() {
    return generateResponseForGET(DataHandler.getInstance().getCodeSnippetList());
  }

  @Override
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getSingle(@QueryParam("uuid") String uuid) {
    var data = DataHandler.getInstance().getCodeSnippetByUUID(uuid);

    return generateResponseForGET(data, uuid);
  }
}
