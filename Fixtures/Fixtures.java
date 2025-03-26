package Fixtures;
import java.util.*;
//Not to be mistaken for Fixture class, the Fixtures class contains the arraylist containing all currently added fixtures. It also provides multiple methods of finding, searching, adding, and removing fixtures. This shall not be mistaken for the PlumbingCodeDefinitions Arraylist as that contains all the Fixture definitions as given per code.
public class Fixtures {
    private ArrayList<Fixture> FixturesList;
    public Fixtures() {
        FixturesList = new ArrayList<Fixture>();
    }

    public boolean getFixturesType(String fixtureName) {
        return false;
    }

    public void addFixture(String fixtureName, boolean fixtureType) {
        //Iterate throughout the PlumbingCode array, and check to see if any fixture matches the fixturename. if not, dont add it and return error.
    }

    public boolean setFixtureType(String fixtureName, boolean fixtureType) {
        return false;
        //Sets a fixtures type (public or private)
    }

    public void removeFixture(String fixtureName) {
        //Search for fixturename and remove all occurances of the fixture.
    }

    public void removeAllFixtures() {
        //Same thing as removeFixture but removes everything
    }
    //Returns the length of the array
    public int getFixturesArrayLength() {
        return FixturesList.size();
    }
}
