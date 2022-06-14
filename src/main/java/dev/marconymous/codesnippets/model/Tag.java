package dev.marconymous.codesnippets.model;


import dev.marconymous.codesnippets.annotations.UUIDValidOrNull;
import jakarta.validation.constraints.Size;
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
  @UUIDValidOrNull
  private String UUID;

  /**
   * The name of the tag.
   */
  @Size(min = 1, max = 25)
  private String name;
}
