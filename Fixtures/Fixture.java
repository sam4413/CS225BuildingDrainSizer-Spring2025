package fixtures;

public abstract class Fixture {
    private int quantity;
    private boolean isPublic;

    //Define a new abstract fixture 
    public Fixture(int m_quantity, boolean m_isPublic) {
        quantity = Math.abs(m_quantity);
        isPublic = m_isPublic;
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

    public abstract double getBaseDFU();

    public abstract double getMinimumTrapSize();

    public double getTotalDFU() {
        return getBaseDFU() * getQuantity();
    }

    @Override
    public String toString() {
        return "x"+getQuantity()+" ";
    }
}
