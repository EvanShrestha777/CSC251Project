public class Policy {
    // Fields for Policy
    private int policyNumber;
    private String providerName;
    private PolicyHolder policyHolder;  // HAS-A relationship

    // Static field to count Policy objects
    private static int policyCount = 0;

    // Constructor
    public Policy(int policyNumber, String providerName, PolicyHolder policyHolder) {
        this.policyNumber = policyNumber;
        this.providerName = providerName;

        // SECURITY: create a new PolicyHolder object instead of direct reference
        this.policyHolder = new PolicyHolder(
                policyHolder.getFirstName(),
                policyHolder.getLastName(),
                policyHolder.getAge(),
                policyHolder.getSmokingStatus(),
                policyHolder.getHeight(),
                policyHolder.getWeight()
        );

                policyCount++;
    }

    // Getter for PolicyHolder (returns a copy for security)
    public PolicyHolder getPolicyHolder() {
        return new PolicyHolder(
                policyHolder.getFirstName(),
                policyHolder.getLastName(),
                policyHolder.getAge(),
                policyHolder.getSmokingStatus(),
                policyHolder.getHeight(),
                policyHolder.getWeight()
        );
    }

    // Getters for Policy fields
    public int getPolicyNumber() { return policyNumber; }
    public String getProviderName() { return providerName; }

    // Static getter for policy count
    public static int getPolicyCount() { return policyCount; }

    // Calculate policy price
    public double calculatePrice() {
        double basePrice = 600.0;
        double ageFee = (policyHolder.getAge() > 50) ? 75.0 : 0.0;
        double smokerFee = policyHolder.isSmoker() ? 100.0 : 0.0;
        return basePrice + ageFee + smokerFee;
    }

    // toString method
    @Override
    public String toString() {
        return "Policy Number: " + policyNumber +
               "\nProvider Name: " + providerName +
               "\n" + policyHolder +
               "\nPolicy Price: $" + String.format("%.2f", calculatePrice());
    }
}