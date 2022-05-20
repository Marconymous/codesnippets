package dev.marconymous.codesnippets.services;

import dev.marconymous.codesnippets.Config;
import jakarta.ws.rs.core.Response;

public abstract class CRUDService {
  @SuppressWarnings("unused")
  public abstract Response getAll();
  @SuppressWarnings("unused")
  public abstract Response getSingle(String uuid);

  protected final String UUID_REGEX = Config.getProperty("uuid.regexp");

  protected final boolean uuidIsInvalid(String uuid) {
    return uuid == null || !uuid.matches(UUID_REGEX);
  }

  protected final Response generateResponseForGET(Object data) {
    return generateResponseForGET(data, null, false);
  }

  protected final Response generateResponseForGET(Object data, String uuid) {
    return generateResponseForGET(data, uuid, true);
  }

  private Response generateResponseForGET(Object data, String uuid, boolean checkUUID) {
    if (uuidIsInvalid(uuid) && checkUUID) {
      throw new RuntimeException("Invalid UUID: " + uuid);
    }

    if (data == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }

    return Response.ok().entity(data).build();
  }
}
