package lib;

public abstract class Input {
    public static final String exit = "exit";
    public static final String back = "back";

    public static void maybeExit(String input) {
        if (input.equalsIgnoreCase(exit)) {
            System.exit(0);
        }
    }

    public static boolean isBack(String input) {
        return input.equalsIgnoreCase(back);
    }
}
