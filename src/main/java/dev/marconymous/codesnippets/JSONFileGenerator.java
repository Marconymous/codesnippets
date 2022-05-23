package dev.marconymous.codesnippets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.marconymous.codesnippets.model.CodeSnippet;
import dev.marconymous.codesnippets.model.ProgrammingLanguage;
import dev.marconymous.codesnippets.model.Tag;
import dev.marconymous.codesnippets.model.Visibility;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

public class JSONFileGenerator {
  /**
   * Generates a JSON file with a single code snippet.
   *
   * @param args The arguments.
   */
  public static void main(String[] args) {
    ProgrammingLanguage[] languages = {
      new ProgrammingLanguage("Java", "Java is a general-purpose computer programming language that is concurrent, class-based, object-oriented, and specifically designed to have as few implementation dependencies as possible.", "img/java.jpg"),
      new ProgrammingLanguage("Python", "Python is an interpreted, high-level, general-purpose programming language.", "img/python.jpg"),
      new ProgrammingLanguage("Kotlin", "Kotlin is a general-purpose, statically typed, compiled, and extensible programming language.", "img/kotlin.jpg")
    };

    Tag[] tags = {
      new Tag("for-loop"),
      new Tag("if-statement"),
      new Tag("while-loop")
    };

    CodeSnippet[] snippets = {
      new CodeSnippet("Java For Loop", "for (int i = 0; i < 10; i++) {\nSystem.out.println(i);\n}", new Date(), Visibility.PUBLIC, null, languages[0]),
      new CodeSnippet("Python If Statement", "if (x < 0):\nprint('x is smaller than 0')", new Date(), Visibility.PUBLIC, null, languages[1]),
      new CodeSnippet("Kotlin While Loop", "while (x < 10) {\nprintln(x)\nx++\n}", new Date(), Visibility.PUBLIC, null, languages[2])
    };

    for (var cs : snippets) {
      cs.setUUID(UUID.randomUUID().toString());
    }

    for (var t : tags) {
      t.setUUID(UUID.randomUUID().toString());
    }

    for (var l : languages) {
      l.setUUID(UUID.randomUUID().toString());
    }

    for (int i = 0; i < tags.length; i++) {
      snippets[i].addTag(tags[i]);
    }

    ObjectMapper om = new ObjectMapper();
    try {
      om.writeValue(Paths.get("/home/marconymous/git/codesnippets/src/main/resources/snippets.json").toFile(), snippets);
      om.writeValue(Paths.get("/home/marconymous/git/codesnippets/src/main/resources/languages.json").toFile(), languages);
      om.writeValue(Paths.get("/home/marconymous/git/codesnippets/src/main/resources/tags.json").toFile(), tags);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
