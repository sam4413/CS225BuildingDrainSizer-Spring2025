package utils;
import java.io.File;

import DrainSizer.FileIO;
public class Log {
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
        System.out.println(Color.WHITE+"[DEBUG]\t"+x.toString()+Color.RESET);
    }

    public static void main(String[] args) {
        String testString = "This is a formatted string.";
        int testInt = 100;
        double testDouble = 83.4583;
        char testChar = 'E';

        Log.out("The quick fox jumped over the lazy dog");
        Log.debug("The quick fox jumped over the lazy dog");
        Log.info("The quick fox jumped over the lazy dog");
        Log.warn("The quick fox jumped over the lazy dog");
        Log.error("The quick fox jumped over the lazy dog");
        Log.fatal("The quick fox jumped over the lazy dog");
        Log.outf("The quick fox jumped over the lazy dog.\nString: %s\nInt: %d \nDouble (2 dec): %.2f\nChar: %c", testString, testInt, testDouble, testChar);
    }

}
