package drainsizer;

//This is the list of all the fixtures list provided by code.
public class PlumbingCodeDefinition {
    

    private String fixtureName;
    private double fixtureDfu;
    private double fixtureDrainTrap;

    public PlumbingCodeDefinition(String m_fixtureName, double m_fixtureDfu, double m_fixtureDrainTrap) {
        fixtureName = m_fixtureName;
        fixtureDfu = m_fixtureDfu;
        fixtureDrainTrap = m_fixtureDrainTrap;
    }

    public double getFixtureDfu() {
        return fixtureDfu;
    }
    public double getFixtureDrainTrap() {
        return fixtureDrainTrap;
    }
    public String getFixtureName() {
        return fixtureName;
    }

    @Override
    public String toString() {
        return fixtureName + " - " + fixtureDfu + " - " + fixtureDrainTrap;
    }
    
}