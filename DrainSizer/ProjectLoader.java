package drainsizer;

import java.util.*;

import utils.Color;
import utils.Log;

//This class essentially goes over the entirety of the saving / loading features in the program. This will heavily rely on FileIO class.
public class ProjectLoader {
    
    public void displayProjectMenu(Scanner input) {
        String projectName = "placeholder";
        int fixtureCount = 99;
        Log.out(Color.GREEN+"== Building Drain Sizer ==");
        Log.outf("Project "+Color.YELLOW+"%s"+Color.RESET+"Loaded.", projectName);
        Log.outf("Loaded "+Color.YELLOW+"%d"+Color.RESET+"fixture(s).", fixtureCount);
        Log.out(Color.YELLOW + "\n---OPTIONS------------------------"+Color.RESET+
        
        """
            1. View Fixtures
            2. Add Fixture(s)
            3. Modify Fixture(s)
            4. Remove Fixture(s)
            4. Calculate DFU
            5. Calculate Pipe Size
            6. Save Project
            7. Exit 
        """);
    }   

    public boolean loadProjectFromFile(String filePath) {
        return false;
    }

    public boolean saveProject(String filePath) {
        return false;
    }

    public boolean createNewProject(String filePath) {
        return false;
    }

    // Export all given calculated data to a csv file.
    public boolean exportData(String filePath) {
        return false;
    }

}