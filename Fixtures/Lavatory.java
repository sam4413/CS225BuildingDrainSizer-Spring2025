package fixtures;

public class Lavatory extends Fixture {

    public Lavatory(int m_quantity, boolean m_isPublic) {
        super(m_quantity, m_isPublic);
    }

    @Override
    public double getBaseDFU() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBaseDFU'");
    }

    @Override
    public double getMinimumTrapSize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMinimumTrapSize'");
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString() + "Lavatory - (" + (getIsPublic() == true ? "public" : "private") + ")";
    }
    
}
