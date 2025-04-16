package fixtures;

public class FloorDrain extends Fixture {
    private double minTrapSize;

    // Note the extra parameters: baseDFU and minTrapSize
    public FloorDrain(int m_quantity, boolean m_isPublic, double m_baseDfu, double m_trapSize) {
        super(m_quantity, m_isPublic, m_baseDfu);
        m_trapSize = minTrapSize;
    }

    @Override
    public double getMinimumTrapSize() {
        return (minTrapSize == -100) ? calculateDefaultTrapSize() : minTrapSize;
    }
    
    private double calculateDefaultTrapSize() {
        return super.getBaseDFU(); 
    }
    
    @Override
    public String toString() {
        String usage = getIsPublic() ? "public" : "private";
        return super.toString() + "Floor drain - (" + usage + ") [DFU: " + getBaseDFU() + ", Trap Size: " + getMinimumTrapSize() + "]";
    }
}
