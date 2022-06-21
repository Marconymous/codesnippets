package dev.marconymous.codesnippets.services;

import dev.marconymous.codesnippets.data.DataHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("user")
public class UserService {

  @POST
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/login")
  public Response login(
    @FormParam("username") String user,
    @FormParam("password") String pass,
    @Context HttpServletRequest request
  ) {
    var session = request.getSession();

    if (session.getAttribute("user") != null) {
      return Response.ok().entity("Already logged in!").build();
    }

    var loggedInUser = DataHandler.getUser(user, pass);

    session.setAttribute("user", loggedInUser.second());

    return Response.status(loggedInUser.first() ? 200 : 404).entity(loggedInUser.second()).build();
  }
}
