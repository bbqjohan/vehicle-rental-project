public abstract class Input {
  static final String exit = "exit";
  static final String back = "back";

  static void maybeExit(String input) {
    if (input.equalsIgnoreCase(exit)) {
      System.exit(0);
    }
  }

  static boolean isBack(String input) {
    return input.equalsIgnoreCase(back);
  }
}
