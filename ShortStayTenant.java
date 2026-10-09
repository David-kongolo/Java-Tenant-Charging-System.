 
public class ShortStayTenant extends Tenant {

    private int nights;
    private static final double NIGHTLY_RATE = 25000;

    public ShortStayTenant(
            String id,
            String customerName,
            String phoneNumber,
            int nights) throws InvalidTenantException {

        super(id, customerName, phoneNumber, false);

        if (nights <= 0) {
            throw new InvalidTenantException(
                "Number of nights must be greater than zero."
            );
        }

        this.nights = nights;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights)
            throws InvalidTenantException {
        if (nights <= 0) {
            throw new InvalidTenantException(
                "Number of nights must be greater than zero."
            );
        }
        this.nights = nights;
    }

    @Override
    protected double calculateBaseCharge() {
        return NIGHTLY_RATE * nights;
    }

    @Override
    public double calculateCharge() {
        return calculateBaseCharge();
    }

    @Override
    public String getTenantType() {
        return "Short Stay Tenant";
    }
}