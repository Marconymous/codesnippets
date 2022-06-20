package dev.marconymous.codesnippets.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * Represents an application user.
 */
@Data
public class ApplicationUser extends User {
  /**
   * The Date when the user was created.
   */
  private Date signUpDate;

  /**
   * The list of the user's favorite snippets.
   */
  @ToString.Exclude
  private List<CodeSnippet> favoriteSnippets;

  /**
   * The list of languages the user knows.
   */
  @ToString.Exclude
  private List<ProgrammingLanguage> languages;

  /**
   * The list of the user's created snippets.
   */
  @ToString.Exclude
  private List<CodeSnippet> createdSnippets;
}
