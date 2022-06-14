package dev.marconymous.codesnippets.model;


import dev.marconymous.codesnippets.annotations.UUIDValidOrNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.FormParam;
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
  @FormParam("uuid")
  private String UUID;

  /**
   * The name of the tag.
   */
  @Size(min = 1, max = 25)
  @NotBlank
  @NotNull
  @FormParam("name")
  private String name;
}
