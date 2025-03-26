package DrainSizer;
import java.util.ArrayList;

public class PlumbingCode {
    /*
     * This class encapsulates all the code values read from the input file.
     * What it does is that there is an arraylist of all available fixtures with their respective values. 
     * The ArrayList gets populated based off a csv file of the actual florida plumbing code. 
     * Every time a method calls to get the dfu or any aspect of the thing, it will search the list to then return the respective result.
     */
    
    //This reads the csv file and returns a string of the csv file to be parsed.
    public String readCodeFile(String filePath) {
        return null;
    }

    //This method basically uses the readCodeFile() method to then parse the csv data and assemble it into the arraylist. 
    public void assembleFixtureDefinitions() {

    }

    public double getDfuForType(String fixtureType) {
        return -1.0;
    }
    

}
