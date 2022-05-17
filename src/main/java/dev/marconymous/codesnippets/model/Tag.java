package dev.marconymous.codesnippets.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Tag {

  private String UUID;
  private String name;
  @JsonIgnore
  private List<CodeSnippet> codeSnippets;

  public Tag() {
    this(null);
  }

  public Tag(String name) {
    this(name, new ArrayList<>());
  }

  public Tag(String name, List<CodeSnippet> codeSnippets) {
    this.name = name;
    this.codeSnippets = codeSnippets;
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

  public String getUUID() {
    return UUID;
  }

  public void setUUID(String UUID) {
    this.UUID = UUID;
  }
}
