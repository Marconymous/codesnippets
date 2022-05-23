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
  private static List<ProgrammingLanguage> languageList;
  private static List<CodeSnippet> codeSnippetList;
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


  private static void readTagFile() {
    var data = readFileToList(Config.getProperty("tags.path"), Tag[].class);
    setTagList(data);
  }

  private static void readCodeSnippetFile() {
    var data = readFileToList(Config.getProperty("snippets.path"), CodeSnippet[].class);
    setCodeSnippetList(data);
  }

  private static void readLanguageFile() {
    var data = readFileToList(Config.getProperty("languages.path"), ProgrammingLanguage[].class);
    setLanguageList(data);
  }

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

  public static List<ProgrammingLanguage> getLanguageList() {
    return languageList;
  }

  private static void setLanguageList(List<ProgrammingLanguage> languageList) {
    DataHandler.languageList = languageList;
  }

  public static List<CodeSnippet> getCodeSnippetList() {
    return codeSnippetList;
  }

  private static void setCodeSnippetList(List<CodeSnippet> codeSnippetList) {
    DataHandler.codeSnippetList = codeSnippetList;
  }

  public static List<Tag> getTagList() {
    return tagList;
  }

  private static void setTagList(List<Tag> tagList) {
    DataHandler.tagList = tagList;
  }

  public static CodeSnippet getCodeSnippetByUUID(String uuid) {
    return codeSnippetList.stream().filter(codeSnippet -> codeSnippet.getUUID().equals(uuid)).findFirst().orElse(null);
  }

  public static ProgrammingLanguage getLanguageByUUID(String uuid) {
    return languageList.stream().filter(language -> language.getUUID().equals(uuid)).findFirst().orElse(null);
  }

  public static Tag getTagByUUID(String uuid) {
    return tagList.stream().filter(tag -> tag.getUUID().equals(uuid)).findFirst().orElse(null);
  }
}