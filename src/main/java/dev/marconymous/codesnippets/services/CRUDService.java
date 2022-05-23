package dev.marconymous.codesnippets.services;

import dev.marconymous.codesnippets.Config;
import jakarta.ws.rs.core.Response;

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
    if (uuidIsInvalid(uuid) && checkUUID || data == null) {
      throw new IllegalArgumentException("Invalid UUID: " + uuid);
    }

    return Response.ok().entity(data).build();
  }
}
