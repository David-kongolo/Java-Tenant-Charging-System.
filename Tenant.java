
public abstract class Tenant implements Chargeable {

    private String id;
    private String customerName;
    private String phoneNumber;
    private boolean discountApplies;

    public Tenant(String id, String customerName,
                  String phoneNumber, boolean discountApplies)
            throws InvalidTenantException {

        if (id == null || id.trim().isEmpty()) {
            throw new InvalidTenantException("ID cannot be blank.");
        }
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new InvalidTenantException("Customer name cannot be blank.");
        }
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new InvalidTenantException("Phone number cannot be blank.");
        }

        this.id = id;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.discountApplies = discountApplies;
    }

    public String getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isDiscountApplies() {
        return discountApplies;
    }

    public void setCustomerName(String customerName)
            throws InvalidTenantException {
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new InvalidTenantException("Customer name cannot be blank.");
        }
        this.customerName = customerName;
    }

    public void setPhoneNumber(String phoneNumber)
            throws InvalidTenantException {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new InvalidTenantException("Phone number cannot be blank.");
        }
        this.phoneNumber = phoneNumber;
    }

    public void setDiscountApplies(boolean discountApplies) {
        this.discountApplies = discountApplies;
    }

    public abstract String getTenantType();

    protected abstract double calculateBaseCharge();

    public double getDiscount() {
        if (discountApplies) {
            return calculateBaseCharge() * 0.13;
        }
        return 0;
    }

    @Override
    public double calculateCharge() {
        return calculateBaseCharge() - getDiscount();
    }

    public double getOriginalCharges() {
        return calculateBaseCharge();
    }
}