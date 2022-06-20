package dev.marconymous.codesnippets.model;

import dev.marconymous.codesnippets.annotations.UUIDValidOrNull;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.FormParam;
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
  @FormParam("uuid")
  @UUIDValidOrNull
  private String UUID;

  /**
   * The name of the Programming Language
   */
  @FormParam("name")
  @NotNull
  private String name;

  /**
   * Description of the Programming Language
   */
  @FormParam("description")
  @NotNull
  private String description;

  /**
   * The URL for the image of the Programming Language
   */
  @FormParam("imageUrl")
  @NotNull
  private String imageUrl;
}
