package dev.marconymous.codesnippets;

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
  /**
   * REGEX used for validating UUIDs
   */
  public static final String UUID_REGEX = "|[\\da-fA-F]{8}-([\\da-fA-F]{4}-){3}[\\da-fA-F]{12}";

  /**
   * Generates a random UUID
   *
   * @return a random UUID as a String
   */
  public static String UUIDString() {
    return UUID.randomUUID().toString();
  }

  /**
   * Checks if any of the given Objects are null
   *
   * @param objs the Objects to check
   * @return true if any of the given Objects are null, false otherwise
   */
  public static boolean anyIsNull(Object... objs) {
    return Arrays.stream(objs).anyMatch(Objects::isNull);
  }

  /**
   * @author Lokesh Gupta(<a href="https://howtodoinjava.com/java/java-security/aes-256-encryption-decryption/">https://howtodoinjava.com/java/java-security/aes-256-encryption-decryption/</a>)   *
   */
  public static class AES256 {
    /**
     * The secret key to be used for encryption and decryption
     */
    private static final String SECRET_KEY = Config.getProperty("aes.secret.key");

    /**
     * The salt to be used for encryption and decryption
     */
    private static final String SALT = Config.getProperty("aes.secret.salt");

    /**
     * Will execute the given Executable with the given parameters
     *
     * @param exec the Executable to execute
     * @return the result of the Executable
     */
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

    /**
     * Encrypts the given String
     *
     * @param strToEncrypt the String to encrypt
     * @return the encrypted String
     */
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

    /**
     * Decrypts the given String
     *
     * @param strToDecrypt the String to decrypt
     * @return the decrypted String
     */
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

    private interface Executable {
      String execute(IvParameterSpec ivSpec, SecretKeyFactory factory, KeySpec spec, SecretKey tmp, SecretKeySpec secretKey) throws IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException;
    }
  }

  public static class Roles {
    /**
     * The role of a user
     */
    public static final String USER = "user";

    /**
     * The role of an admin
     */
    public static final String ADMIN = "admin";

    /**
     * The role of someone who is logging in before the 2FA is completed
     */
    public static final String LOGGING_IN = "loggedIn";
  }
}