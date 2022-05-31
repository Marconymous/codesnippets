package dev.marconymous.codesnippets.model;

import dev.marconymous.codesnippets.annotations.ValidUUID;

/**
 * Class Representing a Programming Language
 */
public class ProgrammingLanguage {

  /**
   * The UUID of the Programming Language
   */
  @ValidUUID
  private String UUID;

  /**
   * The name of the Programming Language
   */
  private String name;

  /**
   * Description of the Programming Language
   */
  private String description;

  /**
   * The URL for the image of the Programming Language
   */
  private String imageUrl;

  public ProgrammingLanguage() {
    this(null, null, null);
  }

  public ProgrammingLanguage(String name, String description, String imageUrl) {
    this.name = name;
    this.description = description;
    this.imageUrl = imageUrl;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getUUID() {
    return UUID;
  }

  public void setUUID(String UUID) {
    this.UUID = UUID;
  }
}
