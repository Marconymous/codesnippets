package dev.marconymous.codesnippets.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.marconymous.codesnippets.Config;
import dev.marconymous.codesnippets.model.CodeSnippet;
import dev.marconymous.codesnippets.model.ProgrammingLanguage;
import dev.marconymous.codesnippets.model.Tag;

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
  private static List<ProgrammingLanguage> languageList;

  /**
   * List of all Code Snippets
   */
  private static List<CodeSnippet> codeSnippetList;

  /**
   * List of all Tags
   */
  private static List<Tag> tagList;

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
    var data = readFileToList(Config.getProperty("tags.path"), Tag[].class);
    setTagList(data);
  }

  /**
   * Reads all Code Snippets from the JSON-file
   */
  private static void readCodeSnippetFile() {
    var data = readFileToList(Config.getProperty("snippets.path"), CodeSnippet[].class);
    setCodeSnippetList(data);
  }

  /**
   * Reads all programming languages from the JSON-file
   */
  private static void readLanguageFile() {
    var data = readFileToList(Config.getProperty("languages.path"), ProgrammingLanguage[].class);
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

  /**
   * Returns a list of all programming languages
   *
   * @return a list of all programming languages
   */
  public static List<ProgrammingLanguage> getLanguageList() {
    return languageList;
  }

  /**
   * Setter for Language List
   *
   * @param languageList the new language list
   */
  private static void setLanguageList(List<ProgrammingLanguage> languageList) {
    DataHandler.languageList = languageList;
  }

  /**
   * Getter for Code Snippet List
   *
   * @return a list of all Code Snippets
   */
  public static List<CodeSnippet> getCodeSnippetList() {
    return codeSnippetList;
  }

  /**
   * Setter for Code Snippet List
   *
   * @param codeSnippetList the new Code Snippet List
   */
  private static void setCodeSnippetList(List<CodeSnippet> codeSnippetList) {
    DataHandler.codeSnippetList = codeSnippetList;
  }

  /**
   * Getter for Tag List
   *
   * @return a list of all Tags
   */
  public static List<Tag> getTagList() {
    return tagList;
  }

  /**
   * Setter for Tag List
   *
   * @param tagList the new Tag List
   */
  private static void setTagList(List<Tag> tagList) {
    DataHandler.tagList = tagList;
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
}