
public class Main {

    private static InputHelper input = new InputHelper();
    private static TenantManager manager = new TenantManager("VU");

    public static void main(String[] args) {
        int choice = 0;

        do {
            System.out.println("\n=== HOSTEL CHARGING SYSTEM ===");
            System.out.println("1. Add Resident Tenant");
            System.out.println("2. Add Short Stay Tenant");
            System.out.println("3. Display All Tenants");
            System.out.println("4. Search Tenant");
            System.out.println("5. Remove Tenant");
            System.out.println("6. Display Summary");
            System.out.println("7. Exit");

            try {
                choice = input.readPositiveInt("Enter your choice: ");

                switch (choice) {
                    case 1:
                        addResidentTenant();
                        break;
                    case 2:
                        addShortStayTenant();
                        break;
                    case 3:
                        displayReport();
                        break;
                    case 4:
                        findTenant();
                        break;
                    case 5:
                        removeTenant();
                        break;
                    case 6:
                        displaySummary();
                        break;
                    case 7:
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Choose a number from 1 to 7.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 7);
    }

    private static void addResidentTenant() {
        String name = input.readNonBlank("Customer name: ");
        String phone = input.readNonBlank("Phone number: ");
        int months = input.readPositiveInt("Number of months: ");

        try {
            String id = manager.generateId();
            Tenant tenant =
                new ResidentTenant(id, name, phone, months);

            manager.addTenant(tenant);
            System.out.println("Tenant added successfully.");
            displayTenant(tenant);
        } catch (InvalidTenantException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addShortStayTenant() {
        String name = input.readNonBlank("Customer name: ");
        String phone = input.readNonBlank("Phone number: ");
        int nights = input.readPositiveInt("Number of nights: ");

        try {
            String id = manager.generateId();
            Tenant tenant =
                new ShortStayTenant(id, name, phone, nights);

            manager.addTenant(tenant);
            System.out.println("Tenant added successfully.");
            displayTenant(tenant);
        } catch (InvalidTenantException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void displayReport() {
        manager.sortByCustomerName();

        if (manager.getTenants().isEmpty()) {
            System.out.println("No tenant records available.");
            return;
        }

        for (Tenant tenant : manager.getTenants()) {
            displayTenant(tenant);
            System.out.println("---------------------------");
        }
    }

    private static void displayTenant(Tenant tenant) {
        System.out.println("ID: " + tenant.getId());
        System.out.println("Customer: " + tenant.getCustomerName());
        System.out.println("Phone: " + tenant.getPhoneNumber());
        System.out.println("Type: " + tenant.getTenantType());
        System.out.printf("Original charge: UGX %,.0f%n",
                tenant.getOriginalCharges());
        System.out.printf("Discount: UGX %,.0f%n",
                tenant.getDiscount());
        System.out.printf("Final charge: UGX %,.0f%n",
                tenant.calculateCharge());
    }

    private static void findTenant() {
        String id = input.readId("Enter tenant ID: ");

        try {
            displayTenant(manager.findById(id));
        } catch (RecordNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removeTenant() {
        String id = input.readId("Enter tenant ID: ");

        try {
            Tenant tenant = manager.findById(id);

            if (input.readYesNo("Remove " +
                    tenant.getCustomerName() + "? (yes/no): ")) {
                manager.removeById(id);
                System.out.println("Tenant removed.");
            } else {
                System.out.println("Removal cancelled.");
            }
        } catch (RecordNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displaySummary() {
        System.out.println("Total records: "
                + manager.getNumberOfRecords());
        System.out.printf("Total charges: UGX %,.0f%n",
                manager.getTotalCharges());
        System.out.printf("Total discounts: UGX %,.0f%n",
                manager.getTotalDiscount());
    }
}