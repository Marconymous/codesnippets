package dev.marconymous.codesnippets.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTransient;

import java.util.ArrayList;
import java.util.List;

public class ProgrammingLanguage {

  private String UUID;
  private String name;

  private String description;
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
