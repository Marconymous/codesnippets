package dev.marconymous.codesnippets.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Class representing a user.
 */
@Data
public class User {
  /**
   * The user's UUID.
   */
  private String userUUID;

  /**
   * The user's username.
   */
  private String userName;

  /**
   * The user's password.
   */
  private String password;
}
