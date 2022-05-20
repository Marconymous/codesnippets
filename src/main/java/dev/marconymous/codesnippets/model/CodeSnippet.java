package dev.marconymous.codesnippets.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.marconymous.codesnippets.data.DataHandler;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTransient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CodeSnippet {

  private String UUID;
  private String title;
  private String content;
  private Date creationDate;

  @JsonIgnore
  @JsonbTransient
  private List<Tag> tags;
  private Visibility visibility;
  private ApplicationUser creator;
  @JsonIgnore
  @JsonbTransient
  private ProgrammingLanguage programmingLanguage;

  public CodeSnippet() {
    this.tags = new ArrayList<>();
  }

  public CodeSnippet(String title, String content, Date creationDate, Visibility visibility, ApplicationUser creator, ProgrammingLanguage programmingLanguage) {
    this.title = title;
    this.content = content;
    this.creationDate = creationDate;
    this.visibility = visibility;
    this.creator = creator;
    this.programmingLanguage = programmingLanguage;
    this.tags = new ArrayList<>();
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  public void addTag(Tag tag) {
    tags.add(tag);
  }

  public Visibility getVisibility() {
    return visibility;
  }

  public void setVisibility(Visibility visibility) {
    this.visibility = visibility;
  }

  public ApplicationUser getCreator() {
    return creator;
  }

  public void setCreator(ApplicationUser creator) {
    this.creator = creator;
  }

  public ProgrammingLanguage getProgrammingLanguage() {
    return programmingLanguage;
  }

  public void setProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
    this.programmingLanguage = programmingLanguage;
  }

  public String getUUID() {
    return UUID;
  }

  public void setUUID(String UUID) {
    this.UUID = UUID;
  }

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
      addTag(tag);
    }
  }
}
