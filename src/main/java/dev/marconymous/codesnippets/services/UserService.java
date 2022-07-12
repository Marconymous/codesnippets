package dev.marconymous.codesnippets.services;

import dev.marconymous.codesnippets.data.DataHandler;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

import static dev.marconymous.codesnippets.Utils.AES256.decrypt;
import static dev.marconymous.codesnippets.Utils.AES256.encrypt;
import static dev.marconymous.codesnippets.Utils.Roles.LOGGING_IN;
import static dev.marconymous.codesnippets.data.DataHandler.TWO_FA_KEYS;

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
    var token = user + ";" + LOGGING_IN + ";" + userRole;

    var cookie = new NewCookie(
      "token",
      encrypt(token),
      "/",
      "",
      "Auth-Token",
      600,
      false
    );

    return Response.ok().cookie(cookie)
      .entity(loggedInUser.getUserName() + " logged in! (not 2FA)").build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/get2FA")
  @RolesAllowed(LOGGING_IN)
  public Response get2FA(
    @CookieParam("token") String token
  ) {
    var words = new String[]{"book", "arrow", "chance", "woolen", "pack", "enclose", "critic", "will", "polish", "talk", "shop", "hunger", "hire", "cruel", "mouse"};
    var random = words[(int) (Math.random() * words.length)];


    var decrypted = decrypt(token);
    TWO_FA_KEYS.put(decrypted, random);

    return Response.ok().entity("{\"request\": \"" + random + "\"}").build();
  }

  /**
   * Used to validate the 2FA-request
   *
   * @param twoFA the 2FA-request
   * @param token the token of the user
   * @return a cookie with userdata
   */
  @POST
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/2fa")
  @RolesAllowed(LOGGING_IN)
  public Response login2FA(
    @FormParam("2fa") String twoFA,
    @CookieParam("token") String token
  ) {
    var decrypted = decrypt(token);
    var expected = TWO_FA_KEYS.get(decrypted);
    TWO_FA_KEYS.remove(decrypted);
    if (expected.equals(twoFA)) {
      var newValue = decrypted.split(";");
      newValue[1] = newValue[2];

      var newLoginCookie = new NewCookie(
        "token",
        encrypt(newValue[0] + ";" + newValue[1] + ";" + newValue[2]),
        "/",
        "",
        "Auth-Token",
        600,
        false
      );

      return Response.ok().cookie(newLoginCookie).entity("2FA successful").build();
    } else {
      return Response.status(400).entity("2FA failed").build();
    }
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
