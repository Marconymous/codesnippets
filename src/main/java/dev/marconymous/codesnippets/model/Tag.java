package dev.marconymous.codesnippets.model;

import java.util.List;

public class Tag {
  private String name;
  private List<CodeSnippet> codeSnippets;

  public Tag() {
    this(null);
  }

  public Tag(String name) {
    this.name = name;
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
}
