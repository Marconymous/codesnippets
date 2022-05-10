package dev.marconymous.codesnippets.model;

import java.util.Date;
import java.util.List;

public class CodeSnippet {
  private String title;
  private String content;
  private Date creationDate;
  private List<Tag> tags;
  private Visibility visibility;
  private ApplicationUser creator;
  private ProgrammingLanguage programmingLanguage;

  public CodeSnippet() {
  }

  public CodeSnippet(String title, String content, Date creationDate, Visibility visibility, ApplicationUser creator, ProgrammingLanguage programmingLanguage) {
    this.title = title;
    this.content = content;
    this.creationDate = creationDate;
    this.visibility = visibility;
    this.creator = creator;
    this.programmingLanguage = programmingLanguage;
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
}
