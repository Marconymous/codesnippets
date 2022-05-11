package dev.marconymous.codesnippets;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ApplicationPath("/api")
public class Config extends Application {
  public static final String ALLOWED_ORIGINS = "*";
}