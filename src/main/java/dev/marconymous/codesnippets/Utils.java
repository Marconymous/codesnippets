package dev.marconymous.codesnippets;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class Utils {
  public static final String UUID_REGEX = "|[\\da-fA-F]{8}-([\\da-fA-F]{4}-){3}[\\da-fA-F]{12}";
  public static String UUIDString() {
    return UUID.randomUUID().toString();
  }

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
