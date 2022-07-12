package dev.marconymous.codesnippets.services;

import dev.marconymous.codesnippets.Config;
import dev.marconymous.codesnippets.Utils;
import dev.marconymous.codesnippets.annotations.UUIDValidNotNull;
import dev.marconymous.codesnippets.exceptions.WithStatusException;
import jakarta.ws.rs.core.Response;

import static dev.marconymous.codesnippets.Utils.Roles.ADMIN;
import static dev.marconymous.codesnippets.Utils.Roles.USER;
import static jakarta.ws.rs.core.Response.Status.UNAUTHORIZED;

/**
 * Class to implement CRUD operations.
 */
public abstract class CRUDService {
  protected final String UUID_REGEX = Config.getProperty("uuid.regexp");

  /**
   * List all items of class.
   *
   * @return Response with list of items.
   */
  @SuppressWarnings("unused")
  public abstract Response getAll();

  /**
   * Get item by UUID.
   *
   * @param uuid UUID of item.
   * @return Response with item.
   */
  @SuppressWarnings("unused")
  public abstract Response getSingle(String uuid);

  /**
   * Delete item by UUID.
   *
   * @param uuid UUID of item.
   * @return Response for deletion.
   */
  public abstract Response delete(@UUIDValidNotNull String uuid);

  /**
   * Check if UUID is valid.
   *
   * @param uuid UUID to check.
   * @return True if UUID is valid, false otherwise.
   */
  protected final boolean uuidIsInvalid(String uuid) {
    return uuid == null || !uuid.matches(UUID_REGEX);
  }

  /**
   * Generates Response for GET.
   *
   * @param data Data to return.
   * @return Response with data.
   */
  protected final Response generateResponseForGET(Object data) {
    return generateResponseForGET(data, null, false);
  }

  /**
   * Generates Response for GET with UUID.
   *
   * @param data Data to return.
   * @param uuid UUID of item.
   * @return Response with data.
   */
  protected final Response generateResponseForGET(Object data, String uuid) {
    return generateResponseForGET(data, uuid, true);
  }

  /**
   * Generates Response for GET.
   *
   * @param data      Data to return.
   * @param uuid      UUID of item.
   * @param checkUUID If true, check if UUID is valid.
   * @return Response with data.
   */
  private Response generateResponseForGET(Object data, String uuid, boolean checkUUID) {
    if ((uuidIsInvalid(uuid) || data == null) && checkUUID) {
      throw new IllegalArgumentException("Invalid UUID: " + uuid);
    }

    return Response.ok().entity(data).build();
  }

  /**
   * Method to validate if a user is logged in and has the correct role
   *
   * @param token        the token of the user
   * @param requiredRole the required role
   */
  protected final void validateLogin(String token, String requiredRole) {
    var decrypted = Utils.AES256.decrypt(token);
    if (decrypted == null) throw new WithStatusException("You are not authorized", UNAUTHORIZED);
    var vals = decrypted.split(";");
    if (vals.length < 2) throw new WithStatusException("You are not authorized", UNAUTHORIZED);
    if (!checkRole(requiredRole, token))
      throw new WithStatusException("You are not authorized", UNAUTHORIZED);
  }

  private boolean checkRole(String req, String current) {
    if (!req.equals(USER) && !req.equals(ADMIN)) return false;

    if (req.equals(current)) return true;

    return req.equals(USER) && current.equals(ADMIN);
  }
}
