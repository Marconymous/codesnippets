package dev.marconymous.codesnippets.model;

/**
 * Class representing a user.
 */
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

  public User() {
  }

  public User(String userUUID, String userName, String password) {
    this.userUUID = userUUID;
    this.userName = userName;
    this.password = password;
  }

  public String getUserUUID() {
    return userUUID;
  }

  public void setUserUUID(String userUUID) {
    this.userUUID = userUUID;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
