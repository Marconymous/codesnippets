package dev.marconymous.codesnippets.services;

import dev.marconymous.codesnippets.Utils;
import dev.marconymous.codesnippets.data.DataHandler;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

import static dev.marconymous.codesnippets.Utils.Roles.ADMIN;
import static dev.marconymous.codesnippets.Utils.Roles.USER;

@Path("user")
public class UserService {

  /**
   * Login to use Services
   *
   * @param user the username of the User
   * @param pass the password of the user
   * @return a cookie with userdata
   */
  @POST
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/login")
  @PermitAll
  public Response login(
    @FormParam("username") String user,
    @FormParam("password") String pass
  ) {
    var loggedInUser = DataHandler.getUser(user, pass);
    var userRole = loggedInUser.getRole();
    var token = user + ";" + userRole;

    var cookie = new NewCookie(
      "token",
      Utils.AES256.encrypt(token),
      "/",
      "",
      "Auth-Token",
      600,
      false
    );

    return Response.ok().cookie(cookie)
      .entity(loggedInUser.getUserName() + " logged in!").build();
  }

  /**
   * Logout for Users
   *
   * @return a new Login Cookie with no data
   */
  @GET
  @Path("/logout")
  @Produces(MediaType.TEXT_PLAIN)
  @PermitAll
  public Response logout() {
    NewCookie tokenCookie = new NewCookie(
      "token",
      "",
      "/",
      "",
      "Auth-Token",
      1,
      false
    );

    return Response
      .ok()
      .entity("Logged Out!")
      .cookie(tokenCookie)
      .build();
  }
}
