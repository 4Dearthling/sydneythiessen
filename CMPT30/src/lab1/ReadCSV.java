package lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/*use arrays.asLis(array (String[])).contains(element (string))*/

public class ReadCSV {
    public static void main(String[] args) {
        String csvFileName = "Property_Assessment_Data_2025.csv";

        try {
            String[][] data = readData(csvFileName);
            //printData(data);
            recordCount(data);
            assessedValueMinMax(data);
            wardCount(data);
            propertyAssessmentClass(data);
        } catch (IOException e) {
            System.out.println("Failed to read " + csvFileName);
        }
    }

    /**
     * Read the contents of a CSV file and return data as a 2D array of String.
     *
     * @param csvFileName - the CSV file name
     * @return data - the values in the CSV file
     * @throws IOException - IO exception
     */
    private static String[][] readData(String csvFileName) throws IOException {
        String[][] data;
        int index = 0;
        // Try-with-resources statement to create a stream to read the CSV file. Automatically closes the resource.
        try (BufferedReader reader = Files.newBufferedReader(Path.of(csvFileName))) {
            // Skip the header - this assumes the first line is a header
            reader.readLine();

            // Create 2D array to store all rows of data as String
            int initialSize = 100;
            data = new String[initialSize][];

            // Read the file line by line and store all rows into a 2D array
            String line;
            while ((line = reader.readLine()) != null) {
                // Split a line by comma works for CSV files
                String[] values = line.split(",");

                // Check if the array is full
                if (index == data.length)
                // Array is full, create and copy all values to a larger array
                {
                    data = Arrays.copyOf(data, data.length * 2);
                }

                data[index++] = values;
            }
        }

        // Remove empty rows in the array and return it
        return Arrays.copyOf(data, index);
    }

        /**
     * Print all rows of data.
     *
     * @param data - 2D array containing data
     */
    private static void printData(String[][] data) {
        System.out.println("The number of records: " + data.length);
        for (String[] row : data) {
            System.out.println("Date: " + row[0]);
            System.out.println("Symbol: " + row[1]);
            System.out.printf("Closing price: $%.2f\n", Double.parseDouble(row[3]));
        }
    }

    private static void recordCount(String[][] data) {
        System.out.println("The number of records: " + data.length);
    }

   private static void assessedValueMinMax(String[][] data) {
        int assessedValueCol = 8;
        int min = 0;
        int max = 0;

        try{
            min = Integer.parseInt(data[0][8]);
            max = min;
        } catch (NumberFormatException e) {
            System.out.println("The first value in the list is not an integer");
        }

        for (String[] row: data) {
            int currentAssessedValue = Integer.parseInt(row[assessedValueCol]);
            if (currentAssessedValue < min){
                min = currentAssessedValue;
            }
            if (currentAssessedValue > max){
                max = currentAssessedValue;
            }
        }

       System.out.println("The minimum assessed value is: " + min);
       System.out.println("The maximum assessed value is: " + max);
   }

   private static void wardCount(String[][] data) {
        String[] wards = new String[1];
        int index = 0;
        int wardCol = 7;

        for (String[] row: data) {
            if (row[wardCol].isEmpty() || Arrays.asList(wards).contains(row[wardCol])){
                continue;
            }

            if (index == wards.length){
                //resize array
                wards = Arrays.copyOf(wards, wards.length * 2);
            }

            wards[index++] = row[wardCol];
        }
        System.out.println("The number of wards: " + index);
   }

   private static void propertyAssessmentClass(String[][] data) {
       String[] assessClasses = new String[1];
       int index = 0;
       int assessClassCol = 15;

       for (String[] row: data) {
           if (row[assessClassCol].isEmpty() || Arrays.asList(assessClasses).contains(row[assessClassCol])){
               continue;
           }

           if (index == assessClasses.length){
               //resize array
               assessClasses = Arrays.copyOf(assessClasses, assessClasses.length * 2);
           }

           assessClasses[index++] = row[assessClassCol];
       }

       System.out.println("The Property Assessment Classes in Edmonton are: " + Arrays.toString(Arrays.copyOf(assessClasses, index)));

   }
}

