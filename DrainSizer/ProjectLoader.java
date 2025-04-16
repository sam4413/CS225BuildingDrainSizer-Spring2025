package drainsizer;

import java.util.*;

import fixtures.Fixture;
import fixtures.Fixtures;
import fixtures.FloorDrain;
import fixtures.KitchenSink;
import fixtures.Lavatory;
import fixtures.WaterCloset;
import utils.Color;
import utils.ExceptionHelper;
import utils.InputHelper;
import utils.Log;

//This class essentially goes over the entirety of the saving / loading features in the program. This will heavily rely on FileIO class.
public class ProjectLoader {

    // This is the project creation Wizard. This will call the respective actions to
    // CREATE A NEW PROJECT.
    public void projectCreator(Scanner input) {
        Log.out(Color.GREEN + "== Building Drain Sizer ==");
        String projectName = InputHelper.processStringChoice(input, "Create a new project named as: ");
        Log.out(Color.GREEN + projectName);
        String confirmation = InputHelper.processStringChoice(input,
                "Create a project named '" + Color.YELLOW + projectName + Color.RESET + "'? (Y/N): ");
        if (confirmation.equalsIgnoreCase("N")) {
            Log.out(Color.RED + "Operation aborted. Returning to project menu.");
            return;
        }

        String path = InputHelper.processStringChoice(input,
                "Enter the exact folder path you want to save your file in:");
        if (createNewProject(path + "\\" + projectName + ".bdsp")) {
            Log.out(Color.GREEN + "Project '" + projectName + "' created successfully at: " + path);
        } else {
            Log.error("Failed to create the project. Please check the file path and try again.");
        }
    }

    // Return the modified fixtures object.
    public Fixtures loadProjectFromFile(String filePath, Fixtures fixtures, PlumbingCode code) {
        FileIO fileIO = new FileIO(filePath);
        String fileContent = fileIO.readFile();

        if (fileContent == null || fileContent.isEmpty()) {
            Log.error("Failed to read file or file is empty: " + filePath);
            return null;
        }

        try {
            boolean inFixturesBlock = false;
            fixtures.removeAllFixtures(); // Clear existing fixtures

            // Split the file content into lines
            String[] lines = fileContent.split("\n");
            for (String string : lines) {
                Log.debug(string);
            }
            Log.fatal(lines.length);
            for (String line : lines) {
                // Skip comments
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                
                if (line.startsWith("projectName:")) {
                    String projectName = line.split(":",2)[1];
                    Log.fatal(line);
                    fixtures.setProjectName(projectName);
                    Log.debug("Project name set to: " + projectName);
                    continue;
                }

                // Fixtures block start
                if (line.equals("fixtures[")) {
                    inFixturesBlock = true;
                    continue;
                } else if (line.equals("]")) {
                    inFixturesBlock = false;
                    continue;
                }

                if (inFixturesBlock) {
                    // Parse fixture entries
                    String[] parts = line.split("[:,]", 3);
                    if (parts.length == 3) {
                        String fixtureName = parts[0].trim();
                        int quantity = Integer.parseInt(parts[1].trim());
                        boolean isPublic = Boolean.parseBoolean(parts[2].trim());

                        // Add the fixture to the Fixtures object
                        Log.debug(
                            "FixtureLoader:\n"+
                            "\tAdding fixture: "+fixtureName+"\n"+
                            "\tWith quantity: "+quantity+"\n"+
                            "\tWith visibility: "+Boolean.toString(isPublic)+"\n"
                        );
                        addFixtureToFixtures(fixtureName, quantity, isPublic, fixtures, code);
                    } else {
                        Log.warn("Invalid fixture entry: " + line);
                    }
                }
            }

            Log.info("Project loaded successfully from: " + filePath);
            return fixtures;
        } catch (Exception e) {
            Log.error("Error processing file content: " + ExceptionHelper.stackToString(e));
            return null;
        }
    }

    private void addFixtureToFixtures(String fixtureName, int quantity, boolean isPublic, Fixtures fixtures, PlumbingCode plumbingCode) {
        // Retrieve the fixture definition from PlumbingCode
        PlumbingCodeDefinition definition = plumbingCode.getFixtureByName(fixtureName);

        if (definition == null) {
            Log.warn("Fixture type '" + fixtureName + "' is not defined in the plumbing code.");
            return;
        }

        // Add or update the fixture in the Fixtures list
        boolean updatedExisting = false;
        for (Fixture fixture : fixtures.getFixturesList()) {
            if (fixture.getClass().getSimpleName().equalsIgnoreCase(fixtureName) && fixture.getIsPublic() == isPublic) {
                fixture.setQuantity(fixture.getQuantity() + quantity);
                updatedExisting = true;
                break;
            }
        }

        if (!updatedExisting) {
            // Create a new fixture based on the definition
            Fixture newFixture = null;
            switch (fixtureName.toLowerCase()) {
                case "floordrain":
                    newFixture = new FloorDrain(quantity, isPublic, definition.getFixtureDfu(),
                            definition.getFixtureDrainTrap());
                    break;
                case "kitchensink":
                    newFixture = new KitchenSink(quantity, isPublic, definition.getFixtureDfu(),
                            definition.getFixtureDrainTrap());
                    break;
                case "lavatory":
                    newFixture = new Lavatory(quantity, isPublic, definition.getFixtureDfu(),
                            definition.getFixtureDrainTrap());
                    break;
                case "watercloset":
                    newFixture = new WaterCloset(quantity, isPublic, definition.getFixtureDfu(),
                            definition.getFixtureDrainTrap());
                    break;
                default:
                    Log.warn("Unknown fixture type: " + fixtureName);
                    return;
            }
            fixtures.getFixturesList().add(newFixture);
        }
    }

    public boolean saveProject(String filePath, Fixtures fixtures) {
        FileIO fileIO = new FileIO(filePath);

        StringBuilder fileContent = new StringBuilder();

        try {
            // Write project metadata
            fileContent.append("# Building Drain Sizer (c) Adam Ahmad 2025 All Rights Reserved\n");
            fileContent.append("# Project metadata\n");
            fileContent.append("projectName:").append(fixtures.getProjectName()).append("\n");
            fileContent.append("lastsaved:").append(new Date()).append("\n");
            fileContent.append("totalFixtures:").append(fixtures.getFixturesList().size()).append("\n");

            // Write fixtures
            fileContent.append("#fixtures iterable\n");
            fileContent.append("fixtures[\n");
            for (Fixture fixture : fixtures.getFixturesList()) {
                fileContent.append(fixture.getClass().getSimpleName() + ":" + fixture.getQuantity() +","+fixture.getIsPublic()
                + "\n");
            }
            fileContent.append("]\n");

            // Write total stats
            DrainCalc myDrainCalc = new DrainCalc();
            fileContent.append("#totalstats\n");
            fileContent.append("totaldfu:").append(myDrainCalc.calculateBuildingDfu(fixtures)).append("\n");
            fileContent.append("pipesize:").append(myDrainCalc.calculateDrainSize(fixtures)).append("\n");

            // Use FileIO to write the content to the file
            if (fileIO.writeFile(fileContent.toString())) {
                Log.info("Project saved successfully to: " + filePath);
                return true;
            } else {
                Log.error("Failed to save the project to: " + filePath);
                return false;
            }
        } catch (Exception e) {
            Log.error("Error writing file: " + ExceptionHelper.stackToString(e));
            return false;
        }
    }

    // This will create a new .bdsp file with empty data that can be loaded for the
    // project.
    public boolean createNewProject(String filePath) {
        StringBuilder fileContent = new StringBuilder();
        fileContent.append("# Building Drain Sizer\n");
        fileContent.append("projectName:New Project\n");
        fileContent.append("lastsaved:00/00/0000\n");
        fileContent.append("totalFixtures:0\n");
        fileContent.append("#Fixtures Iterable\n");
        fileContent.append("fixtures[\n");
        fileContent.append("FloorDrain:0\n");
        fileContent.append("KitchenSink:0\n");
        fileContent.append("Lavatory:0\n");
        fileContent.append("WaterCloset:0\n");
        fileContent.append("]\n");
        fileContent.append("#Total Stats\n");
        fileContent.append("totaldfu:0\n");
        fileContent.append("pipesize:0\n");
        String pathf = fileContent.toString();
        FileIO fileIO = new FileIO(filePath+".bdsp");
        return fileIO.writeFile(pathf);
    }

    // Export all given calculated data to a csv file.
    public boolean exportData(String filePath) {
        return false;
    }

}