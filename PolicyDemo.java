import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PolicyDemo {
    public static void main(String[] args) {
        ArrayList<Policy> policies = new ArrayList<>();
        int smokerCount = 0;
        int nonSmokerCount = 0;

        try {
            File file = new File("PolicyInformation.txt");
            Scanner inputFile = new Scanner(file);

            while (inputFile.hasNextLine()) {
                String line = inputFile.nextLine().trim();
                if (line.isEmpty())
                    continue;

                int policyNumber = Integer.parseInt(line);
                String providerName = inputFile.nextLine().trim();
                String firstName = inputFile.nextLine().trim();
                String lastName = inputFile.nextLine().trim();
                int age = Integer.parseInt(inputFile.nextLine().trim());
                String smokingStatus = inputFile.nextLine().trim();
                double height = Double.parseDouble(inputFile.nextLine().trim());
                double weight = Double.parseDouble(inputFile.nextLine().trim());

               
                PolicyHolder holder = new PolicyHolder(firstName, lastName, age,
                                                       smokingStatus, height, weight);

                
                Policy policy = new Policy(policyNumber, providerName, holder);
                policies.add(policy);

                
                if (holder.isSmoker())
                    smokerCount++;
                else
                    nonSmokerCount++;
            }

            inputFile.close();

           
            for (Policy policy : policies) {
                System.out.println(policy);
                System.out.println(); 
            }

            
            System.out.println("There were " + Policy.getPolicyCount() + " Policy objects created.");
            System.out.println("The number of policies with a smoker is: " + smokerCount);
            System.out.println("The number of policies with a non-smoker is: " + nonSmokerCount);

        } catch (IOException e) {
            System.out.println("Error: File not found.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format in the file.");
        }
    }
}
