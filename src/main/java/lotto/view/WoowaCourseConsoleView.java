package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.NoSuchElementException;

public abstract class WoowaCourseConsoleView {
    private static final String ERROR_MESSAGE_TEMPLATE = "[ERROR] %s";

    public static void println(String message) {
        System.out.println(message);
    }

    public static void print(String message) {
        System.out.print(message);
    }

    public static void printlnError(String message) {
        println(ERROR_MESSAGE_TEMPLATE.formatted(message));
    }

    public static String input() {
        return Console.readLine();
    }

    protected static void retry(Runnable runnable) {
        while (true) {
            try {
                runnable.run();
                return;
            } catch (NoSuchElementException e) {
                throw e;
            } catch (Exception ignored) {
                printlnError(ignored.getMessage());
            }
        }
    }
}
