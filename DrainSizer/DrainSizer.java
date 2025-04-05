package drainsizer;

import java.util.*;

import fixtures.*;
import utils.*;

//This is the main program. This is basically the main menu of the program. The user can choose between the options and passes the actions to their respective methods. This class also contains the currently stored currently added fixtures.
public class DrainSizer {
    // Main runner method

    //prerunner
    public void prerunner() {
        PlumbingCode code = new PlumbingCode();
        code.assembleFixtureDefinitions("codes.csv");

        ArrayList<PlumbingCodeDefinition> element = code.getFixturesList();
        
        for (PlumbingCodeDefinition thing : element) {
            if (thing != null) {
                Log.info(thing.toString());
            }
            else {

            }
        }
    }

    public void run() {
        try {
            prerunner();
            Scanner input = new Scanner(System.in); // The master scanner. This will be passed around accordingly if needed. 
            int choice = -1;
            do {
                menu(); 
                choice = menuSelector();
            } while (choice != 3);
            

            input.close();
        } catch (Exception err) {
            // One try-catch to handle all the unexpected try catches, and exit gracefully,
            // as it might not be safe to continue.
            Log.fatal(
                    "An unhandled exception has occured. As a result, the program has to close. Please try again or report this issue to the program author.\n"
                            + err.getMessage() + "\nStack Trace:\n" + ExceptionHelper.stackToString(err));
            // Save methods TBD
            System.exit(-1);
        }
    }

    public int menuSelector() {
        int choice = InputHelper.processIntChoice();
        switch (choice) {
            case 1:
                choice = 1;
                Log.error("Not Final");
                FixtureOperations myFixtureOperations = new FixtureOperations();
                myFixtureOperations.fixtureMenu();
                break;
            case 2:
                choice = 2;
                Log.error("NotImplimented");
                break;
            case 3:
                choice = 3;
                Log.out(Color.RED+"Thank you for using Building Drain Sizer. Goodbye.");
                break;
        
            default:
                //Log.error("Invalid value!");
                break;
        }
        return choice;
    }

    // Top most level of the menu. This will allow the user to either create a new
    // project or load from an existing one.
    public void menu() {
        Log.out(Color.GREEN + """
                == Building Drain Sizer ==
                """ + Color.WHITE + "Welcome. Please choose an option:\n" +
                Color.YELLOW + "---OPTIONS------------------------\n" +
                Color.WHITE + """
                        1. Create a new project.
                        2. Load from an existing project.
                        3. Exit.
                        """ +
                Color.YELLOW + "---Developed by: Adam Ahmad-------\n" +
                Color.RED + "v.0.1 alpha " + Color.CYAN + "- nogui - public\n");
    }

    

    public void saveProjectMenu(Scanner input) {

    }

    public void calculateDfuMenu(Scanner input) {

    }

    public void calculateDrainSizeMenu(Scanner input) {

    }

    
}