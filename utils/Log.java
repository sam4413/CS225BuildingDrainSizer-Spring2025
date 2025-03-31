package utils;
import java.io.File;
import java.util.Scanner;

import DrainSizer.FileIO;
public class Log {
    public static boolean DEBUG = false;
    public static boolean NOLOG = false;
    /* 
        **Static method for use approved by Dr. B for logging**
        Static usage (apart from main) will only be seen in the utils package.
        The purpose of this class is to prevent the use of sysout in the actual files, and instead keep it all here.
        This conforms to the requirement of no sysout in any other class but the main class.
        The program also logs all occurances of this into a file located in the logs folder with MM:DD:YYYY notation.
        Logs are outputed as .log for log. It is just a plaintext file. 
    */
    public static void out(Object x) {
        System.out.println(x.toString()+Color.RESET);        
    }
    public static void outf(String format, Object ... args) {
        System.out.println(String.format(format, args));
    }
    public static void info(Object x) {
        System.out.println(Color.WHITE+"[INFO]\t"+x.toString()+Color.RESET);
    }
    public static void warn(Object x) {
        System.err.println(Color.PURPLE+"[WARN]\t"+x.toString()+Color.RESET);
    }
    public static void error(Object x) {
        System.err.println(Color.YELLOW+"[ERROR]\t"+x.toString()+Color.RESET);
    }
    public static void fatal(Object x) {
        System.err.println(Color.RED+"[FATAL]\t"+x.toString()+Color.RESET);
    }
    public static void debug(Object x) {
        if (DEBUG) {
            System.out.println(Color.WHITE+"[DEBUG]\t"+x.toString()+Color.RESET);
        }
    }
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    //Test main (Must be in debug)
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
