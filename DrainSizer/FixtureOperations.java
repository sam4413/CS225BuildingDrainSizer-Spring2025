package drainsizer;
import java.util.*;

import fixtures.*;
import utils.Color;
import utils.InputHelper;
import utils.Log;

//This contains the whole operations for all fixture operations. These include but are not limited to: adding fixtures, removing fixtures, modifying fixtures. 
public class FixtureOperations {
    public void fixtureMenu() {
        int choice = -1;
        do {
            menu();
            choice = menuSelector();
        } while (choice != 8);
    }

    public void menu() {
        String projectName = "testman";
        Log.out(Color.GREEN+"== Building Drain Sizer ==");
        Log.out("Project "+Color.YELLOW+projectName+Color.RESET+" loaded.");
        Log.out(Color.YELLOW + "---OPTIONS------------------------\n");
        Log.out("""
            1. View Fixtures
            2. Add Fixture(s)
            3. Modify Fixture(s)
            4. Remove Fixture(s)
            5. Calculate DFU
            6. Calculate Pipe Size
            7. Save Project
            8. Exit
        """);
        //Color.YELLOW + "---OPTIONS------------------------\n" +
    }
    public int menuSelector() {
        int choice = InputHelper.processIntChoice();
        switch (choice) {
            case 1:
                choice = 1;
                viewFixtures();
                break;
            case 8:
                choice = 8;
                Log.out(Color.RED+"Return to main!");
                break;
        
            default:
                Log.error("Invalid value!");
                break;
        }
        return choice;
    }

    public void addFixture() {
        Log.error("Not added");
    }

    public void removeFixture() {
        Log.error("Not added");
    }

    public void modifyFixture() {
        Log.error("Not added");
    }

    public void viewFixtures() {
        Log.out(Color.GREEN+"All added fixtures currently: ");
        PlumbingCode code = new PlumbingCode();
        for (PlumbingCodeDefinition fixturesList : code.getFixturesList()) {
            Log.info(fixturesList.toString());
        }
    }
    
}