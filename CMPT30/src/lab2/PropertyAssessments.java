package lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class PropertyAssessments {
    private HashMap<Integer, PropertyAssessment> assessments;
    private static final String RED = "\u001B[31m";
    private static int count = 0;

    public PropertyAssessments(String filename){
        assessments = new HashMap<>();
        int index = 0;
        // Try-with-resources statement to create a stream to read the CSV file. Automatically closes the resource.
        try (BufferedReader reader = Files.newBufferedReader(Path.of(filename))) {
            // Skip the header - this assumes the first line is a header
            reader.readLine();
            // Read the file line by line and store all rows into a propertyAssessment hashmap
            String line;
            while ((line = reader.readLine()) != null) {
                // Split a line by comma works for CSV files
                String[] values = line.split(",");
                this.addAssessment(values); //should be a constructor

            }
        } catch (IOException e) {
            System.out.println(RED + "Error: can't open file " + filename);
            System.exit(1);
        }
    }
    public PropertyAssessments(List<PropertyAssessment> propertyAssessments) {
        this.assessments = propertyAssessments.stream().collect(Collectors.toMap(PropertyAssessment::getAccountNumber, assessment -> assessment,(existing, replacement) -> existing,  // Handle duplicates
                HashMap::new));


    }
    public long getCount(){
        return getAssessedValueStats().getCount();
    }
    public double minAssessedValue() {
        return getAssessedValueStats().getMin();
    }

    public double maxAssessedValue() {
        return getAssessedValueStats().getMax();
    }

    public double getMeanAssessedValue() {
        return getAssessedValueStats().getAverage();
    }

    public double getRangeAssessedValue() {
        DoubleSummaryStatistics stats = getAssessedValueStats();
        return stats.getMax() - stats.getMin();
    }

    public double getMedianAssessedValue() {
        List<Double> assessmentValues = assessments.values().stream()
                .map(PropertyAssessment::getAssessedValue).sorted().toList();

        int size = assessmentValues.size();
        if (size == 0) {
            return 0;
        }
        if (size % 2 == 0) {
            return (assessmentValues.get(size / 2) + assessmentValues.get(size / 2 - 1)) / 2;
        } else {
            return assessmentValues.get(size / 2);
        }
    }

    private DoubleSummaryStatistics getAssessedValueStats() {
        return assessments.values().stream().collect(Collectors.summarizingDouble(PropertyAssessment::getAssessedValue));
    }

    public void addAssessment(String[] rowData) {
        House house = new House(rowData[1].isBlank() ? "" : rowData[1], rowData[2].isBlank() ? "" : rowData[2],
                rowData[3].isBlank() ? "" : rowData[3], rowData[4].isBlank() ? "" : rowData[4]);
        Neighborhood neighborhood = new Neighborhood(rowData[5].isBlank() ? -1 : Integer.parseInt(rowData[5]),
                rowData[6].isBlank() ? "" : rowData[6], rowData[7].isBlank() ? "" : rowData[7]);
        Geolocation geolocation = new Geolocation(rowData[9].isBlank() ? BigDecimal.ZERO : new BigDecimal(rowData[9]),
                rowData[10].isBlank() ? BigDecimal.ZERO : new BigDecimal(rowData[10]),
                rowData[11].isBlank() ? "" : rowData[11]);

        int[] assessmentPercentages = new int[3];
        String[] assessmentTypes = new String[3];

        //probably should iterate over this in case more types are added
        assessmentPercentages[0] = (rowData[12].isBlank() ? -1 : Integer.parseInt(rowData[12]));
        assessmentPercentages[1] = (rowData[13].isBlank() ? -1 : Integer.parseInt(rowData[13]));
        assessmentPercentages[2] = (rowData[14].isBlank() ? -1 : Integer.parseInt(rowData[14]));
        assessmentTypes[0] = (rowData[15].isBlank() ? "" : rowData[15]);
        assessmentTypes[1] = (rowData.length > 16 && !rowData[16].isBlank() ? rowData[16] : null);
        assessmentTypes[2] = (rowData.length > 17 && !rowData[17].isBlank() ? rowData[17] : null);

        Assessments assessmentClasses = new Assessments(assessmentPercentages, assessmentTypes);

        PropertyAssessment propertyAssessment = new PropertyAssessment(
                rowData[0].isBlank() ? -1 : Integer.parseInt(rowData[0]), house, neighborhood, rowData[8].isBlank() ? -1 : Float.parseFloat(rowData[8]), geolocation, assessmentClasses);

        assessments.put(Integer.parseInt(rowData[0]), propertyAssessment);
    }

    public int getResidentialCount() {
        return count;
    }
    public PropertyAssessment getAssessment(String accountNumber) {

        int acctNumber = 0;
        try {
            acctNumber = Integer.parseInt(accountNumber);
        } catch (NumberFormatException e) {
            System.out.println("Error: invalid account number...");
            System.exit(1);
        }
        if (!this.containsAssessment(acctNumber)) {
            System.out.println("Property is not found");
            System.exit(1);
        }
        return assessments.get(acctNumber);
    }

    public boolean containsAssessment(int accountNumber) {
        return assessments.containsKey(accountNumber);
    }

    public int size() {
        return assessments.size();
    }
//    public List<PropertyAssessment> getAssessmentsByNeighbourhood(String neighbourhood) {
//
//        List<PropertyAssessment> neighbourhoodAssessments = assessments.values().stream().filter(a -> neighbourhood.equals
//                (a.getNeighborhood().getNeighborhoodName())).toList();
//
//        if (neighbourhoodAssessments.isEmpty()) {
//            System.out.println("Property is not found");
//            System.exit(1);
//        }
//        System.out.println("Statistics (neighbourhood = " + neighbourhood + ")");
//        return neighbourhoodAssessments;
//    }

    public List<PropertyAssessment> getAllAssessments() {
        return new ArrayList<>(assessments.values());
    }

    public PropertyAssessments NeighbourhoodAssessments(String neighbourhood) {
        List<PropertyAssessment> neighbourhoodAssessments = assessments.values().stream().filter(a -> neighbourhood.equals
                (a.getNeighborhood().getNeighborhoodName())).toList();
        return new PropertyAssessments(neighbourhoodAssessments);

    }

    public PropertyAssessments AllAssessments(List<PropertyAssessment> allAssessments) {
        return new PropertyAssessments(allAssessments);
    }

    public PropertyAssessments getAssessmentsByAssessmentClass(String assessmentClass) {
        List<PropertyAssessment> assessmentClassAssessments = assessments.values().stream()
                .filter(a -> {
                    String[] types = a.getAssessment().getAssessmentTypes();
                    for (String type : types) {
                        if (assessmentClass.equals(type)) {
                            return true;
                        }
                    }
                    return false;
                }).collect(Collectors.toList());
                return new PropertyAssessments(assessmentClassAssessments);
    }

}