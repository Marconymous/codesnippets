package dev.marconymous.codesnippets.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Tag class
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
  /**
   * The UUID of the tag.
   */
  private String UUID;

  /**
   * The name of the tag.
   */
  private String name;
}
