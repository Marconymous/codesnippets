package dev.marconymous.codesnippets.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.marconymous.codesnippets.Config;
import dev.marconymous.codesnippets.model.CodeSnippet;
import dev.marconymous.codesnippets.model.ProgrammingLanguage;
import dev.marconymous.codesnippets.model.Tag;
import dev.marconymous.codesnippets.model.Visibility;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static dev.marconymous.codesnippets.Config.getProperty;
import static java.nio.file.Paths.get;

@Path("restore")
public class RestorationService {
  public static ProgrammingLanguage[] languages = {
    new ProgrammingLanguage("c2c79790-814c-43bf-ac32-782709fb2c98", "Java", "Java is a general-purpose computer programming language that is concurrent, class-based, object-oriented, and specifically designed to have as few implementation dependencies as possible.", "img/java.jpg"),
    new ProgrammingLanguage("6dcfb985-4693-4b44-8269-5ef41a59da20", "Python", "Python is an interpreted, high-level, general-purpose programming language.", "img/python.jpg"),
    new ProgrammingLanguage("31098c72-7071-4c56-a723-59c13165c12d", "Kotlin", "Kotlin is a general-purpose, statically typed, compiled, and extensible programming language.", "img/kotlin.jpg")
  };

  public static Tag[] tags = {
    new Tag("4c643935-f395-431b-bff8-8a76fc2050b5", "for-loop"),
    new Tag("b3e41499-8b04-45b9-bb71-c7b2a3a9fbd4", "if-statement"),
    new Tag("bca68468-d7bc-4379-8574-147cab92b29a", "while-loop")
  };

  public static CodeSnippet[] snippets = {
    new CodeSnippet("d9212ce9-09b5-4e22-8820-3d319047b318", "Java For Loop", "for (int i = 0; i < 10; i++) {\nSystem.out.println(i);\n}", new Date(), List.of(tags[0]), Visibility.PUBLIC, null, languages[0]),
    new CodeSnippet("8f694d07-436c-4fbe-b077-b968072f32dd", "Python If Statement", "if (x < 0):\nprint('x is smaller than 0')", new Date(), List.of(tags[0]), Visibility.PUBLIC, null, languages[1]),
    new CodeSnippet("52abdb76-36dd-4c58-862c-2bebc3bcf1dd", "Kotlin While Loop", "while (x < 10) {\nprintln(x)\nx++\n}", new Date(), List.of(tags[0]), Visibility.PUBLIC, null, languages[2])
  };

  @GET
  public Response restore(@QueryParam("pwd") String password) {
    if (!password.equals("reset-all")) {
      return Response.status(Response.Status.BAD_REQUEST).entity("Wrong Password!").build();
    }

    ObjectMapper om = new ObjectMapper();
    try {
      om.writeValue(get(getProperty("snippets.path")).toFile(), snippets);
      om.writeValue(get(getProperty("languages.path")).toFile(), languages);
      om.writeValue(get(getProperty("tags.path")).toFile(), tags);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return Response.ok().entity("Data has been reset!").build();
  }
}
