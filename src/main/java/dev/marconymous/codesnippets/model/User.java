package dev.marconymous.codesnippets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class representing a user.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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

  /**
   * The user's user-role
   */
  private String role;
}
