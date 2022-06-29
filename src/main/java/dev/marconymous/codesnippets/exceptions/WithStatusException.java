package dev.marconymous.codesnippets.exceptions;


import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.core.Response;

public class WithStatusException extends ClientErrorException {
  public WithStatusException(Response.Status status) {
    super(status);
  }
}
