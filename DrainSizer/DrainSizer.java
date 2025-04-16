package drainsizer;

import java.util.*;

import fixtures.*;
import utils.*;

//This is the main program. This is basically the main menu of the program. The user can choose between the options and passes the actions to their respective methods. This class also contains the currently stored currently added fixtures.
public class DrainSizer {
    public PlumbingCode code;
    public Fixtures fixtures;
    // Main runner method

    //prerunner
    public void prerunner() {
        code = new PlumbingCode();
        code.assembleFixtureDefinitions("codes.csv");
        Log.clear();
        ArrayList<PlumbingCodeDefinition> element = code.getFixturesList();
        
        for (PlumbingCodeDefinition thing : element) {
            if (thing != null) {
                Log.info(thing.toString());
            }
            else {

            }
        }

        fixtures = new Fixtures();
    }

    public void run() {
        try {
            prerunner();
            Scanner input = new Scanner(System.in); // The master scanner. This will be passed around accordingly if needed. 
            int choice = -1;
            do {
                menu(); 
                choice = menuSelector(input);
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

    public int menuSelector(Scanner input) {
        int choice = InputHelper.processIntChoice(input, "Please enter an option: ");
        ProjectLoader myProjectLoader = new ProjectLoader();
        switch (choice) {
            case 1:
                choice = 1;
                Log.clear();
                Log.out(Color.GREEN+"== Building Drain Sizer ==");
                Log.out(Color.CYAN+"Project Creation Wizard");
                String inProjectName = InputHelper.processStringChoice(input, "Enter the project name you want: ");
                String inProjectPath = InputHelper.processStringChoice(input, "Enter the folder path you want to save this project to: ");
                Log.out("Is this ok?");
                Log.out(Color.GREEN+"Project Name: "+inProjectName);
                Log.out(Color.GREEN+"Project Directory: "+inProjectPath);
                String confirm = InputHelper.processStringChoice(input, "(y/n): ");
                if (confirm.equalsIgnoreCase("Y")) {
                    if (myProjectLoader.createNewProject(inProjectPath + inProjectName)) {
                        Log.out(Color.GREEN+"Project created successfully!");
                        FixtureOperations myFixtureOperations = new FixtureOperations();
                        myFixtureOperations.fixtureMenu(input, code, fixtures);
                    } else {
                        Log.error("There was an error creating the project. Please ensure the program has access to the folder you are trying to create the project towards.");
                    }
                    
                } else {
                    Log.out(Color.RED+"Project creation aborted.");
                    break;
                }
                
                break;
            case 2:
                choice = 2;
                Log.clear();
                // FixtureOperations myFixtureOperations = new FixtureOperations();
                // myFixtureOperations.fixtureMenu(input, code, fixtures);
                String inPath = InputHelper.processStringChoice(input, "Enter the exact file folder path of the project: ");
                String inConfirmation = InputHelper.processStringChoice(input, "Load project? This will override any unsaved changes! (y/n): ");
                if (!inConfirmation.equalsIgnoreCase("Y")) {
                    Log.out(Color.RED+"Project load aborted."+Color.RESET);
                }
                
                fixtures = myProjectLoader.loadProjectFromFile(inPath, fixtures, code);
                if (fixtures != null) {
                    Log.out("Project loaded successfully.");
                    FixtureOperations myFixtureOperations = new FixtureOperations();
                    myFixtureOperations.fixtureMenu(input, code, fixtures);
                } else {
                    fixtures.removeAllFixtures(); //Remove all fixtures for safety.
                    Log.error("There was an error with the project file, and cannot be read. Please ensure it is not corrupted and try again.");
                }
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
        //Log.clear();
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