package fixtures;

public class Lavatory extends Fixture {
    private double baseDFU;
    private double minTrapSize;

    // Note the extra parameters: baseDFU and minTrapSize
    public Lavatory(int m_quantity, boolean m_isPublic, double baseDFU, double minTrapSize) {
        super(m_quantity, m_isPublic);
        this.baseDFU = baseDFU;
        this.minTrapSize = minTrapSize;
    }

    @Override
    public double getBaseDFU() {
        return baseDFU;
    }

    @Override
    public double getMinimumTrapSize() {
        return (minTrapSize == -100) ? calculateDefaultTrapSize() : minTrapSize;
    }
    
    private double calculateDefaultTrapSize() {
        return baseDFU; 
    }
    
    @Override
    public String toString() {
        String usage = getIsPublic() ? "public" : "private";
        return super.toString() + "Lavatory - (" + usage + ") [DFU: " + getBaseDFU() + ", Trap Size: " + getMinimumTrapSize() + "]";
    }
}
