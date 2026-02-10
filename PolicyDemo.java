import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PolicyDemo
{
    public static void main(String[] args) 
    {
        ArrayList<Policy> policies = new ArrayList<>();
        int smokerCount = 0;
        int nonSmokerCount = 0;

        try {
            File file = new File("PolicyInformation.txt");
            Scanner inputFile = new Scanner(file);

            while (inputFile.hasNextLine())
            {
                // Skip blank lines until we find policyNumber
                String line = inputFile.nextLine().trim();
                if (line.isEmpty())
                    continue;

                int policyNumber = Integer.parseInt(line);  // first line is policyNumber
                String providerName = inputFile.nextLine().trim();
                String firstName = inputFile.nextLine().trim();
                String lastName = inputFile.nextLine().trim();
                int age = Integer.parseInt(inputFile.nextLine().trim());
                String smokingStatus = inputFile.nextLine().trim();
                double height = Double.parseDouble(inputFile.nextLine().trim());
                double weight = Double.parseDouble(inputFile.nextLine().trim());

                Policy policy = new Policy(policyNumber, providerName, firstName,
                                           lastName, age, smokingStatus, height, weight);
                policies.add(policy);

                if (smokingStatus.equalsIgnoreCase("smoker"))
                    smokerCount++;
                else
                    nonSmokerCount++;
            }

            inputFile.close();

            // Display policy information
            for (Policy policy : policies)
            {
                System.out.println("Policy Number: " + policy.getPolicyNumber());
                System.out.println("Provider Name: " + policy.getProviderName());
                System.out.println("Policyholder's First Name: " + policy.getFirstName());
                System.out.println("Policyholder's Last Name: " + policy.getLastName());
                System.out.println("Policyholder's Age: " + policy.getAge());
                System.out.println("Policyholder's Smoking Status (smoker/non-smoker): "
                                   + policy.getSmokingStatus());
                System.out.println("Policyholder's Height: " + policy.getHeight() + " inches");
                System.out.println("Policyholder's Weight: " + policy.getWeight() + " pounds");
                System.out.printf("Policyholder's BMI: %.2f\n", policy.getBMI());
                System.out.printf("Policy Price: $%.2f\n\n", policy.getPrice());
            }

            System.out.println("The number of policies with a smoker is: " + smokerCount);
            System.out.println("The number of policies with a non-smoker is: " + nonSmokerCount);

        } catch (IOException e) {
            System.out.println("Error: File not found.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format in the file.");
        }
    }
}
