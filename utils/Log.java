package utils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import drainsizer.FileIO;

public class Log {
    public static boolean DEBUG = true;
    public static boolean NOLOG = false;

    private static void writeToFile(String level, Object message) {
        LocalDateTime MyDateTime = LocalDateTime.now();
        FileIO myFile = new FileIO("./logs/bdsp-" + MyDateTime.toLocalDate().toString() + ".log");
        DateTimeFormatter MyDateFormatted = DateTimeFormatter.ofPattern("HH:mm:ss.SSSS");
        myFile.appendFile(MyDateTime.format(MyDateFormatted) + " [" + level + "]\t" + message.toString() + "\n");
    }

    public static void out(Object x) {
        System.out.println(x.toString() + Color.RESET);
    }

    public static void outf(String format, Object... args) {
        System.out.println(String.format(format, args));
    }

    public static void info(Object x) {
        System.out.println(Color.WHITE + "[INFO]\t" + x.toString() + Color.RESET);
        writeToFile("INFO", x);
    }

    public static void warn(Object x) {
        System.err.println(Color.PURPLE + "[WARN]\t" + x.toString() + Color.RESET);
        writeToFile("WARN", x);
    }

    public static void error(Object x) {
        System.err.println(Color.YELLOW + "[ERROR]\t" + x.toString() + Color.RESET);
        writeToFile("ERROR", x);
    }

    public static void fatal(Object x) {
        System.err.println(Color.RED + "[FATAL]\t" + x.toString() + Color.RESET);
        writeToFile("FATAL", x);
    }

    public static void debug(Object x) {
        if (DEBUG) {
            System.out.println(Color.WHITE + "[DEBUG]\t" + x.toString() + Color.RESET);
            writeToFile("DEBUG", x);
        }
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Test main
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("\n\nEnter a test string to be printed out: ");
        String test = input.nextLine();
        input.close();

        int testInt = 100;
        double testDouble = 83.4583;
        char testChar = 'E';

        Log.out(test);
        Log.debug(test);
        Log.info(test);
        Log.warn(test);
        Log.error(test);
        Log.fatal(test);
        Log.outf("String: %s\nInt: %d \nDouble (2 dec): %.2f\nChar: %c", test, testInt, testDouble, testChar);
    }
}
