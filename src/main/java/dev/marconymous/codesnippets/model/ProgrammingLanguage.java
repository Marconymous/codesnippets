package dev.marconymous.codesnippets.model;

import dev.marconymous.codesnippets.annotations.ValidUUID;

/**
 * Class Representing a Programming Language
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
