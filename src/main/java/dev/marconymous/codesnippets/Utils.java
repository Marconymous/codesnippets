package dev.marconymous.codesnippets;

import jakarta.ws.rs.CookieParam;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

public class Utils {
  public static final String UUID_REGEX = "|[\\da-fA-F]{8}-([\\da-fA-F]{4}-){3}[\\da-fA-F]{12}";

  public static String UUIDString() {
    return UUID.randomUUID().toString();
  }

  public static void executeIfNotNull(Object obj, Runnable runnable) {
    if (obj != null) runnable.run();
  }

  public static boolean anyIsNull(Object... objs) {
    return Arrays.stream(objs).anyMatch(Objects::isNull);
  }

  public static boolean allAreNull(Object... objs) {
    return Arrays.stream(objs).allMatch(Objects::isNull);
  }

  /**
   * @author Lokesh Gupta(<a href="https://howtodoinjava.com/java/java-security/aes-256-encryption-decryption/">https://howtodoinjava.com/java/java-security/aes-256-encryption-decryption/</a>)   *
   */
  public static class AES256 {
    private static final String SECRET_KEY = Config.getProperty("aes.secret.key");
    private static final String SALT = Config.getProperty("aes.secret.salt");

    private interface Executable {
      String execute(IvParameterSpec ivSpec, SecretKeyFactory factory, KeySpec spec, SecretKey tmp, SecretKeySpec secretKey) throws IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException;
    }

    private static String execute(Executable exec) {
      try {
        byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        IvParameterSpec ivspec = new IvParameterSpec(iv);

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

        return exec.execute(ivspec, factory, spec, tmp, secretKey);

      } catch (Exception e) {
        System.out.println("Error while enc/decrypting: " + e);
      }

      return null;
    }

    public static String encrypt(String strToEncrypt) {
      return execute(
        (ivSpec, factory, spec, tmp, secretKey) -> {
          Cipher cipher;
          cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
          cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
          return Base64.getEncoder()
            .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        });
    }

    public static String decrypt(String strToDecrypt) {
      return execute(
        (ivSpec, factory, spec, tmp, secretKey) -> {
          Cipher cipher;
          try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
          } catch (NoSuchAlgorithmException |
                   NoSuchPaddingException e) {
            throw new RuntimeException(e);
          }
          cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
          return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        });
    }
  }

  public static class Roles {
    public static final String USER = "user";
    public static final String ADMIN = "admin";

    public static final String LOGGIN_IN = "loggedIn";
  }
}