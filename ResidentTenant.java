
public class ResidentTenant extends Tenant {

    private int months;
    private static final double MONTHLY_RATE = 450000;

    public ResidentTenant(
            String id,
            String customerName,
            String phoneNumber,
            int months) throws InvalidTenantException {

        super(id, customerName, phoneNumber, months >= 4);

        if (months <= 0) {
            throw new InvalidTenantException(
                "Number of months must be greater than zero."
            );
        }

        this.months = months;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months)
            throws InvalidTenantException {

        if (months <= 0) {
            throw new InvalidTenantException(
                "Number of months must be greater than zero."
            );
        }

        this.months = months;
        setDiscountApplies(months >= 4);
    }

    @Override
    protected double calculateBaseCharge() {
        return MONTHLY_RATE * months;
    }

    @Override
    public double calculateCharge() {
        return calculateBaseCharge() - getDiscount();
    }

    @Override
    public String getTenantType() {
        return "Resident Tenant";
    }
}