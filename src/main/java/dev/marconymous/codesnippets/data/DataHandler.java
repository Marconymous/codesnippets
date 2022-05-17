package dev.marconymous.codesnippets.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.marconymous.codesnippets.Config;
import dev.marconymous.codesnippets.model.CodeSnippet;
import dev.marconymous.codesnippets.model.ProgrammingLanguage;
import dev.marconymous.codesnippets.model.Tag;

import java.awt.print.Book;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
  private static DataHandler instance = null;
  private List<ProgrammingLanguage> languageList;
  private List<CodeSnippet> codeSnippetList;
  private List<Tag> tagList;

  private String uuidRegex = Config.getProperty("uuid.regexp");

  /**
   * private constructor defeats instantiation
   */
  private DataHandler() {
    readLanguageFile();
    readTagFile();
    readCodeSnippetFile();
  }


  private void readTagFile() {
    var data = readFileToList(Config.getProperty("tags.path"), Tag[].class);
    setTagList(data);
  }

  private void readCodeSnippetFile() {
    var data = readFileToList(Config.getProperty("snippets.path"), CodeSnippet[].class);
    setCodeSnippetList(data);
  }

  private void readLanguageFile() {
    var data = readFileToList(Config.getProperty("languages.path"), ProgrammingLanguage[].class);
    setLanguageList(data);
  }

  private <T> List<T> readFileToList(String path, Class<T[]> clazz) {
    try {
      byte[] jsonData = Files.readAllBytes(
          Paths.get(path)
      );
      ObjectMapper objectMapper = new ObjectMapper();
      T[] books = objectMapper.readValue(jsonData, clazz);

      return Arrays.stream(books).collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<ProgrammingLanguage> getLanguageList() {
    return languageList;
  }

  private void setLanguageList(List<ProgrammingLanguage> languageList) {
    this.languageList = languageList;
  }

  public List<CodeSnippet> getCodeSnippetList() {
    return codeSnippetList;
  }

  private void setCodeSnippetList(List<CodeSnippet> codeSnippetList) {
    this.codeSnippetList = codeSnippetList;
  }

  public List<Tag> getTagList() {
    return tagList;
  }

  private void setTagList(List<Tag> tagList) {
    this.tagList = tagList;
  }

  public CodeSnippet getCodeSnippetByUUID(String uuid) {
    return codeSnippetList.stream().filter(codeSnippet -> codeSnippet.getUUID().equals(uuid)).findFirst().orElse(null);
  }

  public ProgrammingLanguage getLanguageByUUID(String uuid) {
    return languageList.stream().filter(language -> language.getUUID().equals(uuid)).findFirst().orElse(null);
  }

  public Tag getTagByUUID(String uuid) {
    return tagList.stream().filter(tag -> tag.getUUID().equals(uuid)).findFirst().orElse(null);
  }

  /**
   * gets the only instance of this class
   *
   * @return
   */
  public static DataHandler getInstance() {
    if (instance == null)
      instance = new DataHandler();
    return instance;
  }
}