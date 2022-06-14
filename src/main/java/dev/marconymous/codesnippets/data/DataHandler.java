package dev.marconymous.codesnippets.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.marconymous.codesnippets.Config;
import dev.marconymous.codesnippets.model.CodeSnippet;
import dev.marconymous.codesnippets.model.ProgrammingLanguage;
import dev.marconymous.codesnippets.model.Tag;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
  /**
   * List of all programming languages
   */
  @Getter
  @Setter
  private static List<ProgrammingLanguage> languageList;

  /**
   * List of all Code Snippets
   */
  @Getter
  @Setter
  private static List<CodeSnippet> codeSnippetList;

  /**
   * List of all Tags
   */
  @Getter
  @Setter
  private static List<Tag> tagList;

  private static final String LANGUAGE_FILE = Config.getProperty("languages.path");
  private static final String TAGS_FILE = Config.getProperty("tags.path");
  private static final String SNIPPETS_FILE = Config.getProperty("snippets.path");

  static {
    readLanguageFile();
    readTagFile();
    readCodeSnippetFile();
  }

  /**
   * private constructor defeats instantiation
   */
  private DataHandler() {
  }


  /**
   * Reads all Tags from the JSON-file
   */
  private static void readTagFile() {
    var data = readFileToList(TAGS_FILE, Tag[].class);
    setTagList(data);
  }

  /**
   * Reads all Code Snippets from the JSON-file
   */
  private static void readCodeSnippetFile() {
    var data = readFileToList(SNIPPETS_FILE, CodeSnippet[].class);
    setCodeSnippetList(data);
  }

  /**
   * Reads all programming languages from the JSON-file
   */
  private static void readLanguageFile() {
    var data = readFileToList(LANGUAGE_FILE, ProgrammingLanguage[].class);
    setLanguageList(data);
  }

  /**
   * Reads a JSON-file and returns a list of objects
   *
   * @param path  the path to the JSON-file
   * @param clazz the class of the objects
   * @param <T>   the type of the objects
   * @return a list of objects
   */
  private static <T> List<T> readFileToList(String path, Class<T[]> clazz) {
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

  public static void saveCodeSnippetFile() {
    writeFileToList(SNIPPETS_FILE, getCodeSnippetList());
  }

  public static void saveTagFile() {
    writeFileToList(TAGS_FILE, getTagList());
  }

  public static void saveLanguageFile() {
    writeFileToList(LANGUAGE_FILE, getLanguageList());
  }

  private static <T> void writeFileToList(String path, List<T> data) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.writeValue(Paths.get(path).toFile(), data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Gets a Code Snippet by its ID
   *
   * @param uuid the ID of the Code Snippet
   * @return the Code Snippet
   */
  public static CodeSnippet getCodeSnippetByUUID(String uuid) {
    return codeSnippetList.stream().filter(codeSnippet -> codeSnippet.getUUID().equals(uuid)).findFirst().orElse(null);
  }

  /**
   * Gets a Programming Language by its ID
   *
   * @param uuid the ID of the Programming Language
   * @return the Programming Language
   */
  public static ProgrammingLanguage getLanguageByUUID(String uuid) {
    return languageList.stream().filter(language -> language.getUUID().equals(uuid)).findFirst().orElse(null);
  }

  /**
   * Gets a Tag by its ID
   *
   * @param uuid the ID of the Tag
   * @return the Tag
   */
  public static Tag getTagByUUID(String uuid) {
    return tagList.stream().filter(tag -> tag.getUUID().equals(uuid)).findFirst().orElse(null);
  }

  public static void addCodeSnippet(CodeSnippet codeSnippet) {
    codeSnippetList.add(codeSnippet);
    saveCodeSnippetFile();
  }

  public static void addLanguage(ProgrammingLanguage language) {
    languageList.add(language);
    saveLanguageFile();
  }

  public static void addTag(Tag tag) {
    tagList.add(tag);
    saveTagFile();
  }

  public static void removeCodeSnippet(CodeSnippet codeSnippet) {
    codeSnippetList.remove(codeSnippet);
    saveCodeSnippetFile();
  }

  public static void removeLanguage(ProgrammingLanguage language) {
    languageList.remove(language);
    saveLanguageFile();
  }

  public static void removeTag(Tag tag) {
    tagList.remove(tag);
    saveTagFile();
  }
}