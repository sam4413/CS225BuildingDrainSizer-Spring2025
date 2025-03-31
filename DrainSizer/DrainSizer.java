package DrainSizer;

import java.util.*;
import Fixtures.*; //Fixtures folder
import utils.*;

//This is the main program. This is basically the main menu of the program. The user can choose between the options and passes the actions to their respective methods. This class also contains the currently stored currently added fixtures.
public class DrainSizer {
    // Main runner method
    public void run() {
        try {
            Scanner input = new Scanner(System.in); // The master scanner. This will be passed around accordingly.
            int choice = -1;
            do {
                menu(); 
                choice = processChoice(input);
            } while (choice != 3);
            

            input.close();
        } catch (Exception err) {
            // One try-catch to handle all the unexpected try catches, and exit gracefully,
            // as it might not be safe to continue.
            Log.fatal(
                    "An unhandled exception has occured. As a result, the program has to close. Please try again or report this issue to the program author.\n"
                            + err.getMessage() + "\nStack Trace:\n" + err.getStackTrace());
            // Save methods
            System.exit(-1);
        }
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

    public int processChoice(Scanner input) {
        int outChoice = -1;
        try {
            Log.out("Please enter an option: ");
            String inChoice = input.nextLine();
            int choice = Integer.parseInt(inChoice);
            switch (choice) {
                case 1:
                    outChoice = 1;
                    Log.error("Not added");
                    Log.clear();
                    break;
                case 2:
                    outChoice = 2;
                    Log.error("Not added");
                    Log.clear();
                    break;
                case 3:
                    outChoice = 3;
                    //Log.clear();
                    Log.out(Color.RED+"Thank you for using Building Drain Sizer. Goodbye.");
                    break;
                case 4:
                    outChoice = 4;
                    PlumbingCode myCodeDefinitions = new PlumbingCode();
                    myCodeDefinitions.readCodeFile("test.csv");
                    Log.out(Color.RED+"Testmethod.");
                    break;
                default:
                    break;
            }
            return outChoice;
        } catch (NumberFormatException err) {
            Log.error("Please enter a valid option.");
            return -1;
        } catch (Exception err) {
            Log.error(
                    "An error has occured: " + err.getMessage() + "\nStack Trace: " + err.getStackTrace());
            return -1;
        }

    }

    public void saveProjectMenu(Scanner input) {

    }

    public void calculateDfuMenu(Scanner input) {

    }

    public void calculateDrainSizeMenu(Scanner input) {

    }
}