package drainsizer;

import java.util.*;

import fixtures.*;
import utils.Color;
import utils.InputHelper;
import utils.Log;

//This contains the whole operations for all fixture operations. These include but are not limited to: adding fixtures, removing fixtures, modifying fixtures. 
public class FixtureOperations {
    public PlumbingCode code;
    public Fixtures fixtures;

    public void fixtureMenu(Scanner input, PlumbingCode m_code, Fixtures m_Fixtures) {
        int choice = -1;
        code = m_code;
        fixtures = m_Fixtures;
        do {
            menu();
            choice = menuSelector(input);
        } while (choice != 8);
    }

    public void menu() {
        String projectName = "testman";
        Log.out(Color.GREEN + "== Building Drain Sizer ==");
        Log.out("Project " + Color.YELLOW + projectName + Color.RESET + " loaded.");
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
        // Color.YELLOW + "---OPTIONS------------------------\n" +
    }

    public int menuSelector(Scanner input) {
        int choice = InputHelper.processIntChoice(input, "Please enter an option: ");
        switch (choice) {
            case 1:
                choice = 1;
                viewFixtures(input);
                break;
            case 2:
                addFixture(input);
                break;
            case 3:
                modifyFixture(input);
                break;
            case 4:
                removeFixture(input);
                break;
            case 5:
                calculateDfu(input);
                break;
            case 8:
                choice = 8;
                Log.out(Color.RED + "Return to main!");
                break;

            default:
                Log.error("Invalid value!");
                break;
        }
        return choice;
    }

    public void addFixture(Scanner input) {
        Log.clear();
        Log.out(Color.GREEN + "== Add Fixture ==" + Color.RESET);

        // ensure csv definitions have been loaded.
        ArrayList<PlumbingCodeDefinition> csvDefinitions = code.getFixturesList();
        if (csvDefinitions == null || csvDefinitions.isEmpty()) {
            Log.error("No fixture definitions loaded from CSV.");
            InputHelper.processPause(input, Color.CYAN + "Press any key to continue..." + Color.RESET);
            return;
        }

        Log.out("Available fixture types:");
        for (PlumbingCodeDefinition def : csvDefinitions) {
            Log.out("- " + def.getFixtureName());
        }
        Log.out(".. end of list ..\n");

        String chosenType = InputHelper.processStringChoice(input, "Enter the fixture type to add: ");

        PlumbingCodeDefinition selectedDef = null;
        for (PlumbingCodeDefinition def : csvDefinitions) {
            if (def.getFixtureName().equalsIgnoreCase(chosenType)) {
                selectedDef = def;
                break;
            }
        }
        if (selectedDef == null) {
            Log.error("Fixture type '" + chosenType + "' cannot be found. Please try again.");
            InputHelper.processPause(input, Color.CYAN + "Press any key to continue..." + Color.RESET);
            return;
        }

        int qty = InputHelper.processIntChoice(input, "Enter the number of fixtures to add: ");
        if (qty <= 0) {
            Log.error("Invalid quantity entered.");
            InputHelper.processPause(input, Color.CYAN + "Press any key to continue..." + Color.RESET);
            return;
        }

        String usageInput = InputHelper.processStringChoice(input, "Enter fixture usage type (public/private): ")
                .trim();
        boolean isPublic = usageInput.equalsIgnoreCase("public");

        boolean updatedExisting = false;
        for (Fixture fixture : fixtures.getFixturesList()) {
            if (selectedDef.getFixtureName().equalsIgnoreCase("Floor drains") && fixture instanceof FloorDrain
                    && fixture.getIsPublic() == isPublic) {

                fixture.setQuantity(fixture.getQuantity() + qty);
                updatedExisting = true;
                break;
            }
            if (selectedDef.getFixtureName().equalsIgnoreCase("Floor drains") && fixture instanceof FloorDrain
                    && fixture.getIsPublic() == isPublic) {

                fixture.setQuantity(fixture.getQuantity() + qty);
                updatedExisting = true;
                break;
            }
        }

        // add to array list based on user input (this is bad i know)
        if (!updatedExisting) {
            Fixture newFixture = null;
            if (selectedDef.getFixtureName().equalsIgnoreCase("Floor drains")) {
                newFixture = new FloorDrain(qty, isPublic, selectedDef.getFixtureDfu(),
                        selectedDef.getFixtureDrainTrap());
            } else if (selectedDef.getFixtureName().equalsIgnoreCase("Kitchen sink")) {
                newFixture = new KitchenSink(qty, isPublic, selectedDef.getFixtureDfu(),
                        selectedDef.getFixtureDrainTrap());
            } else if (selectedDef.getFixtureName().equalsIgnoreCase("Lavatory")) {
                newFixture = new Lavatory(qty, isPublic, selectedDef.getFixtureDfu(),
                        selectedDef.getFixtureDrainTrap());
            } else if (selectedDef.getFixtureName().equalsIgnoreCase("Water closet")) {
                newFixture = new WaterCloset(qty, isPublic, selectedDef.getFixtureDfu(),
                        selectedDef.getFixtureDrainTrap());
            }

            if (newFixture == null) {
                Log.error("Fixture type '" + selectedDef.getFixtureName() + "' is not added.");
                InputHelper.processPause(input, Color.CYAN + "Press any key to continue..." + Color.RESET);
                return;
            }

            fixtures.getFixturesList().add(newFixture);
        }

        Log.out(qty + " \"" + selectedDef.getFixtureName() + "\" fixture(s) added as "
                + (isPublic ? "public" : "private") + ".");
        InputHelper.processPause(input, Color.CYAN + "Press any key to continue..." + Color.RESET);
    }

    public void removeFixture(Scanner input) {
        Log.clear();

        String projectName = "testman";
        Log.out(Color.GREEN + "== Remove Fixture ==" + Color.RESET);
        Log.out("Project " + Color.YELLOW + projectName + Color.RESET + " loaded.");

        ArrayList<Fixture> myFixtures = fixtures.getFixturesList();

        if (myFixtures.isEmpty()) {
            Log.out(Color.RED + "No fixtures to remove!" + Color.RESET);
            InputHelper.processPause(input, Color.CYAN + "Press any key to continue..." + Color.RESET);
            return;
        }

        Log.out("\nCurrent fixtures:");
        for (Fixture fixture : myFixtures) {
            Log.out(fixture.toString());
        }
        Log.out(".. end of list ..\n");

        String fixtureToRemove = InputHelper.processStringChoice(input, "Enter the name of the fixture to remove: ")
                .trim();

        int beforeSize = myFixtures.size();
        myFixtures.removeIf(fixture -> fixture.getClass().getSimpleName().equalsIgnoreCase(fixtureToRemove));
        int afterSize = myFixtures.size();

        int removed = beforeSize - afterSize;
        if (removed > 0) {
            Log.out(Color.GREEN + "Successfully removed " + removed + " fixture(s) named '" + fixtureToRemove + "'."
                    + Color.RESET);
        } else {
            Log.out(Color.RED + "No fixtures named '" + fixtureToRemove + "' were found." + Color.RESET);
        }

        InputHelper.processPause(input, Color.CYAN + "Press any key to continue..." + Color.RESET);
    }


    //Will be done by monday (P5.5)
    public void modifyFixture(Scanner input) {
        Log.error("Not added");
    }

    public void viewFixtures(Scanner input) {
        Log.clear();

        String projectName = "testman";
        Log.out(Color.GREEN + "== Building Drain Sizer ==" + Color.RESET);
        Log.out("Project " + Color.YELLOW + projectName + Color.RESET + " loaded.");

        // Get all added fixtures
        ArrayList<Fixture> myFixtures = fixtures.getFixturesList();

        if (myFixtures.isEmpty()) {
            Log.out(Color.RED + "No fixtures have been added yet!" + Color.RESET);
            InputHelper.processPause(input, Color.CYAN + "Press any key to continue..." + Color.RESET);
            return;
        }

        Log.out("\nCurrent fixtures:");

        int totalCount = 0;
        for (Fixture fixture : myFixtures) {
            totalCount += fixture.getQuantity();
            Log.out(fixture.toString());
        }

        Log.out("\nTOTAL FIXTURES: " + totalCount);

        Log.out("\n# OPTIONS:");
        Log.out("1. Go back");

        InputHelper.processPause(input, Color.CYAN + "Press any key to continue..." + Color.RESET);
    }

    public void calculateDfu(Scanner input) {
        DrainCalc myDrainCalc = new DrainCalc();
        double result = myDrainCalc.calculateBuildingDfu(fixtures);
        if (result != -1) {
            Log.out(Color.GREEN+"Total dfu for the building is: "+Color.YELLOW+result+Color.RESET);
            InputHelper.processPause(input, Color.CYAN + "Press any key to continue..." + Color.RESET);
        } else {
            Log.error("There was an error calculating the dfu.");
        }
    }

}