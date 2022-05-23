package dev.marconymous.codesnippets.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents an application user.
 */
public class ApplicationUser extends User {
  /**
   * The Date when the user was created.
   */
  private Date signUpDate;

  /**
   * The list of the user's favorite snippets.
   */
  private List<CodeSnippet> favoriteSnippets;

  /**
   * The list of languages the user knows.
   */
  private List<ProgrammingLanguage> languages;

  /**
   * The list of the user's created snippets.
   */
  private List<CodeSnippet> createdSnippets;

  public ApplicationUser() {
    this.createdSnippets = new ArrayList<>();
    this.favoriteSnippets = new ArrayList<>();
    this.languages = new ArrayList<>();
  }

  public ApplicationUser(Date signUpDate) {
    this();
    this.signUpDate = signUpDate;
  }

  public Date getSignUpDate() {
    return signUpDate;
  }

  public void setSignUpDate(Date signUpDate) {
    this.signUpDate = signUpDate;
  }

  public List<CodeSnippet> getFavoriteSnippets() {
    return favoriteSnippets;
  }

  public void setFavoriteSnippets(List<CodeSnippet> favoriteSnippets) {
    this.favoriteSnippets = favoriteSnippets;
  }

  public List<ProgrammingLanguage> getLanguages() {
    return languages;
  }

  public void setLanguages(List<ProgrammingLanguage> languages) {
    this.languages = languages;
  }

  public List<CodeSnippet> getCreatedSnippets() {
    return createdSnippets;
  }

  public void setCreatedSnippets(List<CodeSnippet> createdSnippets) {
    this.createdSnippets = createdSnippets;
  }
}
