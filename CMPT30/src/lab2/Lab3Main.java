package lab2;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Lab3Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.print("CSV filename: ");
        String filename = scanner.nextLine();
        PropertyAssessments propertyAssessments = new PropertyAssessments(filename);

        System.out.print("Please enter a neighbourhood name: ");
        String neighbourhoodName = scanner.nextLine();
        PropertyAssessments neighbourhoodAssessment = propertyAssessments.NeighbourhoodAssessments(neighbourhoodName.toUpperCase());
        if (neighbourhoodAssessment == null) {
            System.out.println("Sorry, can't find data in " + neighbourhoodName);
            System.exit(0);
        }
        printNeighbourhoodStats(neighbourhoodAssessment, neighbourhoodName);

        System.out.print("Please enter an assessment class: ");
        String assessmentClass = scanner.nextLine();
        PropertyAssessments assessmentClassAssessments = propertyAssessments.getAssessmentsByAssessmentClass(assessmentClass.toUpperCase());
        if (assessmentClassAssessments == null) {
            System.out.println("Sorry, can't find " + assessmentClass + " properties");
            System.exit(0);
        }
        printAssessmentClassStats(assessmentClassAssessments, assessmentClass);
    }

    private static void printNeighbourhoodStats(PropertyAssessments propertyAssessments, String neighbourhood) {
        System.out.println("There are " + moneyFormat(propertyAssessments.getCount()) + " properties in " + neighbourhood);
        System.out.println("The mean value is CAD " + moneyFormat(propertyAssessments.getMeanAssessedValue()));
        System.out.println("The median value is CAD " + moneyFormat(propertyAssessments.getMedianAssessedValue()) + "\n");
    }

    private static void printAssessmentClassStats(PropertyAssessments propertyAssessments, String className) {
        System.out.println("There are " + moneyFormat(propertyAssessments.getCount()) + " " + className + " properties in Edmonton");
        System.out.println("The min value is CAD " + moneyFormat(propertyAssessments.minAssessedValue()));
        System.out.println("The max value is CAD " + moneyFormat(propertyAssessments.maxAssessedValue()) + "\n");
    }

    private static String moneyFormat(double dollars) {
        DecimalFormat money = new DecimalFormat("#,###");
        return money.format(dollars);
    }
}