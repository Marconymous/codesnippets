package dev.marconymous.codesnippets.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.marconymous.codesnippets.data.DataHandler;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTransient;
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
  private String UUID;

  /**
   * The title of the code snippet.
   */
  private String title;

  /**
   * The Content of the code snippet.
   */
  private String content;

  /**
   * The date the code snippet was created.
   */
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
  private Visibility visibility;

  /**
   * The User who created the code snippet.
   */
  private ApplicationUser creator;

  /**
   * The Language for which this snippet is
   */
  @JsonIgnore
  @JsonbTransient
  private ProgrammingLanguage programmingLanguage;


  @SuppressWarnings("unused")
  public String getProgrammingLanguageUUID() {
    return programmingLanguage.getUUID();
  }

  @SuppressWarnings("unused")
  public void setProgrammingLanguageUUID(String programmingLanguageUUID) {
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
}
