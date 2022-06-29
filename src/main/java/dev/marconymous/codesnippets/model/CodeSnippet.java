package dev.marconymous.codesnippets.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.marconymous.codesnippets.annotations.UUIDValidNotNull;
import dev.marconymous.codesnippets.annotations.UUIDValidOrNull;
import dev.marconymous.codesnippets.data.DataHandler;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.FormParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A class representing a code snippet.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeSnippet {
  /**
   * The id of the code snippet.
   */
  @UUIDValidOrNull
  @FormParam("uuid")
  private String UUID;

  /**
   * The title of the code snippet.
   */
  @NotNull
  @NotBlank
  @FormParam("title")
  private String title;

  /**
   * The Content of the code snippet.
   */
  @NotNull
  @NotBlank
  @FormParam("content")
  private String content;

  /**
   * The date the code snippet was created.
   */
  @JsonIgnore
  private Date creationDate;

  /**
   * The list of tags associated with the code snippet.
   */
  @JsonIgnore
  @JsonbTransient
  @ToString.Exclude
  private List<Tag> tags = new ArrayList<>();

  /**
   * The visibility of the code snippet.
   */
  @FormParam("visibility")
  @NotNull
  private Visibility visibility;

  /**
   * The User who created the code snippet.
   */
  private User creator;

  /**
   * The Language for which this snippet is
   */
  @JsonIgnore
  private ProgrammingLanguage programmingLanguage;


  @SuppressWarnings("unused")
  public String getProgrammingLanguageUUID() {
    return programmingLanguage.getUUID();
  }

  @SuppressWarnings("unused")
  @FormParam("programmingLanguageUUID")
  public void setProgrammingLanguageUUID(String programmingLanguageUUID) {
    if (!new UUIDValidNotNull.Validator().isValid(programmingLanguageUUID, null)) {
      throw new IllegalArgumentException("programmingLanguageUUID is not valid or null");
    }

    this.programmingLanguage = DataHandler.getLanguageByUUID(programmingLanguageUUID);
  }

  @SuppressWarnings("unused")
  @JsonbProperty
  public String[] getTagUUIDs() {
    return tags.stream().map(Tag::getUUID).toArray(String[]::new);
  }

  @SuppressWarnings("unused")
  public void setTagUUIDs(String[] tagUUIDs) {
    System.out.println("Called setTagUUIDs");
    for (String tagUUID : tagUUIDs) {
      var tag = DataHandler.getTagByUUID(tagUUID);
      tags.add(tag);
    }
  }

  @SuppressWarnings("unused")
  @FormParam("tagUUIDs")
  public void serviceTagUUID(String tagUUIDs) {
    if (tagUUIDs == null) {
      return;
    }
    var tags = tagUUIDs.split(",");

    this.tags = new ArrayList<>();
    for (String tagUUID : tags) {
      var tag = DataHandler.getTagByUUID(tagUUID);
      this.tags.add(tag);
    }
  }
}
