package dev.marconymous.codesnippets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
