package utils;
import java.io.*;
import utils.*;

public class ExceptionHelper {
    public static String stackToString(Exception err) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        err.printStackTrace(pw);
        return sw.toString();
    }
}
