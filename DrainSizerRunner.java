
import java.io.File;

import DrainSizer.DrainSizer;
//Unpack the method and call the other methods
import utils.Log;

public class DrainSizerRunner {
    boolean guiFlag = false;
    boolean debugFlag = false;
    boolean noLogFlag = false;
    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        DrainSizerRunner runner = new DrainSizerRunner();
        DrainSizer app = new DrainSizer();
        runner.checkArgs(args);
        Log.info("Starting Drain Sizer Program");
        app.run();
    }

    private void checkArgs(String[] args) {
        
    
        for (String arg : args) {
            if (arg.equals("-gui")) {
                guiFlag = true;
                Log.info("Running with GUI mode: " + arg);
            } else if (arg.equals("-debug")) {
                debugFlag = true;
                Log.info("Running with debug mode: " + arg);
            } else if (arg.equals("-test")) {
                debugFlag = true;
                Log.info("Running with test mode: " + arg);
            } 
            
            else {
                Log.warn("Unknown argument: " + arg);
            }
        }
    
        if (guiFlag) {
            Log.fatal("GUI mode is not implemented yet");
        }
    
        if (debugFlag) {
            Log.fatal("Debug mode is not implemented yet");
        }
    }
}