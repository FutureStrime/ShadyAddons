package shady.shady.shady.utils;

public class ThreadUtils {

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch(InterruptedException ignored) {}
    }

}
