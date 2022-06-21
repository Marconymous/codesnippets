package dev.marconymous.codesnippets.data;

public record Tuple<A, B>(A first, B second) {
  public static <SA, SB> Tuple<SA, SB> of(SA first, SB second) {
    return new Tuple<>(first, second);
  }
}
