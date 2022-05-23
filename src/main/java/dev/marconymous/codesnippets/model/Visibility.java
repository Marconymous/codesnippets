package dev.marconymous.codesnippets.model;

public enum Visibility {
  PUBLIC((snippet, user, src) -> true, (snippet, user, src) -> true),
  PRIVATE((snippet, user, src) -> snippet.getCreator().equals(user), (snippet, user, src) -> false),
  UNLISTED((snippet, user, src) -> true, (snippet, user, src) -> false);

  private final UserViewRule viewRule;
  private final UserSuggestionRule suggestRule;

  Visibility(UserViewRule viewRule, UserSuggestionRule suggestRule) {
    this.viewRule = viewRule;
    this.suggestRule = suggestRule;
  }

  public boolean canView(CodeSnippet snippet, User user, Source src) {
    return viewRule.isAllowed(snippet, user, src);
  }

  public boolean canSuggest(CodeSnippet snippet, User user, Source src) {
    return suggestRule.isAllowed(snippet, user, src);
  }

  public enum Source {
    URL,
    WEBAPP
  }

  interface UserViewRule {
    boolean isAllowed(CodeSnippet snippet, User user, Source src);
  }

  interface UserSuggestionRule {
    boolean isAllowed(CodeSnippet snippet, User user, Source src);
  }
}
