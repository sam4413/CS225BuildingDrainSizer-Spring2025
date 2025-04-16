package fixtures;

import java.util.*;

//Not to be mistaken for Fixture class, the Fixtures class contains the arraylist containing all currently added fixtures. It also provides multiple methods of finding, searching, adding, and removing fixtures. This shall not be mistaken for the PlumbingCodeDefinitions Arraylist as that contains all the Fixture definitions as given per code.
public class Fixtures {
    private ArrayList<Fixture> FixturesList;
    private String ProjectName;

    public Fixtures() {
        FixturesList = new ArrayList<Fixture>();
    }

    public Fixture getFixturesType(String fixtureName, boolean isPublic) {
        for (Fixture fixture : FixturesList) {
            if (fixture.getClass().getSimpleName().equalsIgnoreCase(fixtureName) && fixture.getIsPublic() == isPublic) {
                return fixture;
            }
        }
        return null;
    }

    public void addFixture(String fixtureName, boolean fixtureType) {
        // Iterate throughout the PlumbingCode array, and check to see if any fixture
        // matches the fixturename. if not, dont add it and return error.
    }

    public boolean setFixtureType(String fixtureName, boolean fixtureType) {
        return false;
        // Sets a fixtures type (public or private)
    }

    // Remove a fixture based on
    public void removeFixture(String fixtureName) {
        for (Fixture fixture : FixturesList) {
            if (fixture.getClass().getSimpleName().equals(fixtureName)) {

            }
            // Search for fixturename and remove all occurances of the fixture.
        }
    }

    public void removeAllFixtures() {
        FixturesList.clear();
    }

    // Returns the length of the array
    public int getFixturesArrayLength() {
        return FixturesList.size();
    }

    public ArrayList<Fixture> getFixturesList() {
        return FixturesList;
    }

    // NOTE: This was lazily tacked on, however because the ProjectLoader accepts a
    // path and Fixtures object, I don't want to overcomplicate it for the sake of
    // time. So for now, ill just toss it here.
    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

}
