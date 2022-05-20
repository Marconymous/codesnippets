package dev.marconymous.codesnippets.model;

public class Tag {

  private String UUID;
  private String name;

  public Tag() {
    this(null);
  }

  public Tag(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUUID() {
    return UUID;
  }

  public void setUUID(String UUID) {
    this.UUID = UUID;
  }
}
