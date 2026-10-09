
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TenantManager {

    private ArrayList<Tenant> tenants;
    private String idPrefix;
    private int nextNumber;

    public TenantManager(String initials) {
        tenants = new ArrayList<>();
        idPrefix = initials.toUpperCase() + "180";
        nextNumber = 1;
    }

    // Generate ID
    public String generateId() {
        String id = String.format("%s-%03d", idPrefix, nextNumber);
        nextNumber++;
        return id;
    }

    // Add record
    public void addTenant(Tenant tenant) {
        tenants.add(tenant);
    }

    // Find record by ID
    public Tenant findById(String id)
            throws RecordNotFoundException {

        for (Tenant tenant : tenants) {
            if (tenant.getId().equalsIgnoreCase(id)) {
                return tenant;
            }
        }

        throw new RecordNotFoundException(
            "No tenant found with ID: " + id
        );
    }

    // Remove record
    public void removeById(String id)
            throws RecordNotFoundException {

        Tenant tenant = findById(id);
        tenants.remove(tenant);
    }

    // Get all records
    public List<Tenant> getTenants() {
        return tenants;
    }

    // Sort by customer name
    public void sortByCustomerName() {
        tenants.sort(
            Comparator.comparing(
                Tenant::getCustomerName,
                String.CASE_INSENSITIVE_ORDER
            )
        );
    }

    // Calculate total charges
    public double getTotalCharges() {
        double total = 0;

        for (Tenant tenant : tenants) {
            total += tenant.calculateCharge();
        }

        return total;
    }

    // Calculate total discount
    public double getTotalDiscount() {
        double totalDiscount = 0;

        for (Tenant tenant : tenants) {
            totalDiscount += tenant.getDiscount();
        }

        return totalDiscount;
    }

    // Number of records
    public int getNumberOfRecords() {
        return tenants.size();
    }
}