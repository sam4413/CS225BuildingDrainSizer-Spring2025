package drainsizer;

import java.util.ArrayList;

import utils.Log;

public class PlumbingCode {
    /*
     * This class encapsulates all the code values read from the input file.
     * What it does is that there is an arraylist of all available fixtures with
     * their respective values.
     * The ArrayList gets populated based off a csv file of the actual florida
     * plumbing code.
     * Every time a method calls to get the dfu or any aspect of the thing, it will
     * search the list to then return the respective result.
     */

    // This reads the csv file and returns a string of the csv file to be parsed.
    
    public ArrayList<PlumbingCodeDefinition> fixturesList;

    public ArrayList<String> readCodeFile(String filePath) {
        FileIO myFileIO = new FileIO(filePath);
        ArrayList<String> inCSV = myFileIO.readFile("\n");

        // Skip the header
        if (inCSV != null && !inCSV.isEmpty()) {
            inCSV.remove(0);
        }

        return inCSV;
    }

    // This method basically uses the readCodeFile() method to then parse the csv
    // data and assemble it into the arraylist.
    // Populates fixturesList from the CSV data
    public void assembleFixtureDefinitions(String filePath) {
        fixturesList = new ArrayList<PlumbingCodeDefinition>();
        ArrayList<String> csvLines = readCodeFile(filePath);
        if (csvLines == null)
            return;

        for (String line : csvLines) {
            // Remove quotes and trim
            String cleanedLine = line.replaceAll("\"", "").trim();
            String[] parts = cleanedLine.split(",", -1);

            if (parts.length >= 3) {
                try {
                    String fixtureName = parts[0].trim();
                    double dfu = Double.parseDouble(parts[1].trim());
                    double trapSize = Double.parseDouble(parts[2].trim());
                    PlumbingCodeDefinition def = new PlumbingCodeDefinition(fixtureName, dfu, trapSize);
                    fixturesList.add(def);
                    Log.info("Loaded fixture: " + fixtureName + " | DFU: " + dfu + " | Trap: " + trapSize);
                } catch (NumberFormatException e) {
                    Log.error("Failed to parse line: " + line);
                }
            } else {
                Log.error("Invalid line format: " + line);
            }
        }
        /*for (PlumbingCodeDefinition fixture : fixturesList) {
            Log.fatal(fixture.getFixtureName());
            Log.fatal(fixturesList.size());
        }*/
    }

    public double getDfuForType(String fixtureType) {
        return -1.0;
    }

    public ArrayList<PlumbingCodeDefinition> getFixturesList() {
        return fixturesList;
    }

    public PlumbingCodeDefinition getFixtureByName(String name) {
        for (PlumbingCodeDefinition plumbingCodeDefinition : fixturesList) {
            if (plumbingCodeDefinition.getFixtureName().equalsIgnoreCase(name)) {
                return plumbingCodeDefinition;
            }
        }
        return null;
    }

}
