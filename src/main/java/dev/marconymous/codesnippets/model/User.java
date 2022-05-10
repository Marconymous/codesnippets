package dev.marconymous.codesnippets.model;

public class User {
  private String userUUID;
  private String userName;
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
