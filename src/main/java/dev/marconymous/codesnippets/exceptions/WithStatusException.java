package dev.marconymous.codesnippets.exceptions;


import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.core.Response;

/**
 * Exception to throw when a certain status code is returned from the server.
 */
public class WithStatusException extends ClientErrorException {
  public WithStatusException(String message, Response.Status status) {
    super(message, status);
  }
}
