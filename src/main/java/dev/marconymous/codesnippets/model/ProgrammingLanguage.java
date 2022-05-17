package dev.marconymous.codesnippets.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class ProgrammingLanguage {

  private String UUID;
  private String name;
  @JsonIgnore
  private List<CodeSnippet> codeSnippets;
  private String description;
  private String imageUrl;

  public ProgrammingLanguage() {
    this(null, null, null);
  }

  public ProgrammingLanguage(String name, String description, String imageUrl) {
    this.name = name;
    this.description = description;
    this.imageUrl = imageUrl;
    this.codeSnippets = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<CodeSnippet> getCodeSnippets() {
    return codeSnippets;
  }

  public void setCodeSnippets(List<CodeSnippet> codeSnippets) {
    this.codeSnippets = codeSnippets;
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
