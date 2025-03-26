package DrainSizer;
import java.util.*;
import Fixtures.*;

//This is the list of all the fixtures list provided by code.
public class PlumbingCodeDefinitions {
    public ArrayList<PlumbingCodeDefinitions> FixturesList;
    private String FixtureName;
    private double FixtureDfu;
    private double FixtureDrainTrap;

    public PlumbingCodeDefinitions(String m_fixtureName, double m_fixtureDfu, double m_fixtureDrainTrap) {
        FixtureName = m_fixtureName;
        FixtureDfu = m_fixtureDfu;
        FixtureDrainTrap = m_fixtureDrainTrap;
    }

    public double getFixtureDfu() {
        return FixtureDfu;
    }
    public double getFixtureDrainTrap() {
        return FixtureDrainTrap;
    }
    public String getFixtureName() {
        return FixtureName;
    }
    public ArrayList<PlumbingCodeDefinitions> getFixturesList() {
        return FixturesList;
    }
    public PlumbingCodeDefinitions getFixtureByName(String name) {
        return null;
    }
}