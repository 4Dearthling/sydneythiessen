package lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Lab2Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("CSV filename: ");
        String filename = scanner.nextLine();
        PropertyAssessments propertyAssessments = new PropertyAssessments(filename);

        List<PropertyAssessment> allAssessmentsList = propertyAssessments.getAllAssessments();
        PropertyAssessments allAssessments = propertyAssessments.AllAssessments(allAssessmentsList);

        System.out.println("Descriptive statistics of all property assessments");
        System.out.println(printStats(allAssessments));

        System.out.print("\nFind a property assessment by account number: ");
        String accountNumber = scanner.nextLine();
        try {
            accountNumber = String.valueOf(Integer.parseInt(accountNumber));
        } catch (NumberFormatException e) {
            System.out.println("Error: invalid account number...");
            System.exit(1);
        }
        PropertyAssessment assessment = propertyAssessments.getAssessment(accountNumber);
        if (assessment == null) {
            System.out.println("Property is not found");
            System.exit(1);
        }
        System.out.println("Account number = " + accountNumber +
                "\nAddress = " + assessment.getHouse().getAddress() +
                "\nAssessed value = $" + moneyFormat(assessment.getAssessedValue()) +
                "\nAssessment class = " + Arrays.toString(assessment.getAssessment().getAssessmentCollection()) +
                "\nNeighbourhood = " + assessment.getNeighborhood().getNeighborWardString()+
                "\nLocation = " + assessment.getLocation().getLatLongString());

        System.out.print("\nFind statistics by neighbourhood: ");
        String neighbourhood = scanner.nextLine();
        PropertyAssessments neighbourhoodAssessments = propertyAssessments.NeighbourhoodAssessments(neighbourhood.toUpperCase());
        if (neighbourhoodAssessments.getAllAssessments().isEmpty()){
            System.out.println("Neighbourhood is not found");
            System.exit(1);
        }
        System.out.println("Statistics (neighbourhood = " + neighbourhood + ")");
        System.out.println(printStats(neighbourhoodAssessments));

    }

    private static String moneyFormat(double dollars) {
        DecimalFormat money = new DecimalFormat("#,###");
        return money.format(dollars);
    }

    private static String printStats(PropertyAssessments propertyAssessments) {
        return "n = " + propertyAssessments.getCount() +
                "\nmin = $" + moneyFormat(propertyAssessments.minAssessedValue()) +
                "\nmax = $" + moneyFormat(propertyAssessments.maxAssessedValue()) +
                "\nrange = $" + moneyFormat(propertyAssessments.getRangeAssessedValue()) +
                "\nmean = $" + moneyFormat(propertyAssessments.getMeanAssessedValue()) +
                "\nmedian = $" + moneyFormat(propertyAssessments.getMedianAssessedValue());
    }


}





