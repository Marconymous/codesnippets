package dev.marconymous.codesnippets;

import java.util.Arrays;
import java.util.Objects;

public class Utils {
  public static void executeIfNotNull(Object obj, Runnable runnable) {
    if (obj != null) {
      runnable.run();
    }
  }

  public static boolean anyIsNull(Object ... objs) {
    return Arrays.stream(objs).anyMatch(Objects::isNull);
  }

  public static boolean allAreNull(Object ... objs) {
    return Arrays.stream(objs).allMatch(Objects::isNull);
  }
}
