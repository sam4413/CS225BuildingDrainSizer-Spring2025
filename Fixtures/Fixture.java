package fixtures;

public abstract class Fixture {
    private int quantity;
    private boolean isPublic;
    private double baseDfu;
    //Define a new abstract fixture 
    public Fixture(int m_quantity, boolean m_isPublic, double m_baseDfu) {
        quantity = Math.abs(m_quantity);
        isPublic = m_isPublic;
        baseDfu = m_baseDfu;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getBaseDFU() {
        return baseDfu;
    }



    public abstract double getMinimumTrapSize();

    public double getTotalDFU() {
        return getBaseDFU() * getQuantity();
    }

    @Override
    public String toString() {
        return "x"+getQuantity()+" ";
    }
}
