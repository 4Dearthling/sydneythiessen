import lab2.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PropertyAssessmentsTest {
    private PropertyAssessments assessments1;
    private PropertyAssessments assessments2;
    @BeforeEach
    void setUp() throws IOException {
        assessments1 = new PropertyAssessments("test_input.csv");
        assessments2 = new PropertyAssessments("test_input.csv");
    }
    @Test
    void constructorWithFilename() throws IOException {
        PropertyAssessments assessments = new PropertyAssessments("test_input.csv");

        assertEquals(2, assessments.size());

        PropertyAssessment propertyAssess1 = assessments.getAssessment("9999330");
        assertNotNull(propertyAssess1);
        assertEquals(9999330, propertyAssess1.getAccountNumber());
        assertEquals("1713 ROBERTSON PLACE SW", propertyAssess1.getHouse().getAddress().trim());
        assertEquals("Y", propertyAssess1.getHouse().hasGarage());
        assertEquals(5454, propertyAssess1.getNeighborhood().getNeighborhoodID());
        assertEquals("RUTHERFORD (Ipiihkoohkanipiaohtsi Ward)", propertyAssess1.getNeighborhood().getNeighborWardString());
        assertEquals(481500, propertyAssess1.getAssessedValue());
        assertEquals("(53.4205156452796, -113.53161625491973)", propertyAssess1.getLocation().getLatLongString());
        assertArrayEquals(new String[]{"RESIDENTIAL 100%"}, propertyAssess1.getAssessment().getAssessmentCollection());


        PropertyAssessment propertyAssess2 = assessments.getAssessment("10885155");
        assertNotNull(propertyAssess2);
        assertEquals(10885155, propertyAssess2.getAccountNumber());
        assertEquals("4325 34 STREET SW", propertyAssess2.getHouse().getAddress().trim());
        assertEquals("Y", propertyAssess2.getHouse().hasGarage());
        assertEquals(8886, propertyAssess2.getNeighborhood().getNeighborhoodID());
        assertEquals("EDMONTON SOUTH EAST (Sspomitapi Ward)", propertyAssess2.getNeighborhood().getNeighborWardString());
        assertEquals(613000, propertyAssess2.getAssessedValue());
        assertEquals("(53.39215985330776, -113.3903186)", propertyAssess2.getLocation().getLatLongString());
        assertArrayEquals(new String[]{"RESIDENTIAL 95%", "RESIDENTIAL 3%", "FARMLAND 2%"}, propertyAssess2.getAssessment().getAssessmentCollection());


    }
    @Test
    void constructorWithoutFilename(){
        House house1 = new House("", "1713","ROBERTSON PLACE SW","Y");
        Neighborhood neighbourhood1 = new Neighborhood(5454,"RUTHERFORD","Ipiihkoohkanipiaohtsi Ward");
        Geolocation geoloc1 = new Geolocation(new BigDecimal("53.4205156452796"), new BigDecimal("-113.53161625491973"),"POINT (-113.53161625491973 53.4205156452796)");
        Assessments assessments1 = new Assessments(new int[]{100}, new String[]{"RESIDENTIAL"});
            //public PropertyAssessment(double accountNumber, House house, Neighborhood neighborhood, double assessedValue, Geolocation location, Assessments assessment)
        PropertyAssessment propertyAssess1 = new PropertyAssessment(9999330, house1, neighbourhood1, 481500, geoloc1, assessments1);

        House house2 = new House("", "4325","34 STREET SW","Y");
        Neighborhood neighbourhood2 = new Neighborhood(8886,"EDMONTON SOUTH EAST","Sspomitapi Ward");
        Geolocation geoloc2 = new Geolocation(new BigDecimal("53.39215985330776"), new BigDecimal("-113.3903186"),"POINT (-113.39031863530188 53.39215985330776)");
        Assessments assessments2 = new Assessments(new int[]{95,3,2}, new String[]{"RESIDENTIAL", "RESIDENTIAL", "FARMLAND"});

        PropertyAssessment propertyAssess2 = new PropertyAssessment(10885155, house2, neighbourhood2, 613000, geoloc2, assessments2);

        List<PropertyAssessment> propertyAssessmentList = Arrays.asList(propertyAssess1, propertyAssess2);

        PropertyAssessments propertyAssessments1 = new PropertyAssessments(propertyAssessmentList);

        PropertyAssessment propertyAssessCheck1 = propertyAssessments1.getAssessment("9999330");
        assertNotNull(propertyAssessCheck1);
        assertEquals(9999330, propertyAssessCheck1.getAccountNumber());
        assertEquals("1713 ROBERTSON PLACE SW", propertyAssessCheck1.getHouse().getAddress().trim());
        assertEquals("Y", propertyAssessCheck1.getHouse().hasGarage());
        assertEquals(5454, propertyAssessCheck1.getNeighborhood().getNeighborhoodID());
        assertEquals("RUTHERFORD (Ipiihkoohkanipiaohtsi Ward)", propertyAssessCheck1.getNeighborhood().getNeighborWardString());
        assertEquals(481500, propertyAssessCheck1.getAssessedValue());
        assertEquals("(53.4205156452796, -113.53161625491973)", propertyAssessCheck1.getLocation().getLatLongString());
        assertArrayEquals(new String[]{"RESIDENTIAL 100%"}, propertyAssessCheck1.getAssessment().getAssessmentCollection());

        PropertyAssessment propertyAssessCheck2 = propertyAssessments1.getAssessment("10885155");
        assertNotNull(propertyAssessCheck2);
        assertEquals(10885155, propertyAssessCheck2.getAccountNumber());
        assertEquals("4325 34 STREET SW", propertyAssessCheck2.getHouse().getAddress().trim());
        assertEquals("Y", propertyAssessCheck2.getHouse().hasGarage());
        assertEquals(8886, propertyAssessCheck2.getNeighborhood().getNeighborhoodID());
        assertEquals("EDMONTON SOUTH EAST (Sspomitapi Ward)", propertyAssessCheck2.getNeighborhood().getNeighborWardString());
        assertEquals(613000, propertyAssessCheck2.getAssessedValue());
        assertEquals("(53.39215985330776, -113.3903186)", propertyAssessCheck2.getLocation().getLatLongString());
        assertArrayEquals(new String[]{"RESIDENTIAL 95%", "RESIDENTIAL 3%", "FARMLAND 2%"}, propertyAssessCheck2.getAssessment().getAssessmentCollection());

        assertEquals(2, propertyAssessmentList.size());


    }

    @Test
    void getCount() {
        assertEquals(2, assessments1.getCount());
    }

    @Test
    void minAssessedValue() {
        assertEquals(481500, assessments1.minAssessedValue());
    }

    @Test
    void maxAssessedValue() {
        assertEquals(613000, assessments1.maxAssessedValue());
    }

    @Test
    void getMeanAssessedValue() {
        double mean = (double) (481500 + 613000) / 2;
        assertEquals(mean, assessments1.getMeanAssessedValue());
    }

    @Test
    void getRangeAssessedValue() {
        assertEquals(613000 - 481500, assessments1.getRangeAssessedValue());
    }

    @Test
    void getMedianAssessedValue() {
        double median = (double) (481500 + 613000) / 2;
        assertEquals(median, assessments1.getMedianAssessedValue());
    }

    @Test
    void addAssessment() throws IOException {
        assessments1.addAssessment(new String[] {"10001709","","20115","54 AVENUE NW","Y","4461","THE HAMPTONS","sipiwiyiniwak Ward","433000","53.489441704016514","-113.6689852","POINT (-113.66898518190189 53.489441704016514)","100","","","RESIDENTIAL","",""});
        PropertyAssessment propertyAssessCheck1 = assessments1.getAssessment("10001709");
        assertNotNull(propertyAssessCheck1);
        assertEquals(10001709, propertyAssessCheck1.getAccountNumber());
        assertEquals("20115 54 AVENUE NW", propertyAssessCheck1.getHouse().getAddress().trim());
        assertEquals("Y", propertyAssessCheck1.getHouse().hasGarage());
        assertEquals(4461, propertyAssessCheck1.getNeighborhood().getNeighborhoodID());
        assertEquals("THE HAMPTONS (sipiwiyiniwak Ward)", propertyAssessCheck1.getNeighborhood().getNeighborWardString());
        assertEquals(433000, propertyAssessCheck1.getAssessedValue());
        assertEquals("(53.489441704016514, -113.6689852)", propertyAssessCheck1.getLocation().getLatLongString());
        assertArrayEquals(new String[]{"RESIDENTIAL 100%"}, propertyAssessCheck1.getAssessment().getAssessmentCollection());

        assessments2.addAssessment(new String[] {"10001709","","20115","54 AVENUE NW","Y","4461","THE HAMPTONS","sipiwiyiniwak Ward","433000","53.489441704016514","-113.6689852","POINT (-113.66898518190189 53.489441704016514)","100","","","RESIDENTIAL","",""});

        assertEquals(assessments1.getAssessment("10001709"), assessments2.getAssessment("10001709"));

        assertEquals(3, assessments1.size());
        assertEquals(3, assessments2.size());

    }

    @Test
    void getAssessment() {
        assessments1.getAssessment("9999330");
        PropertyAssessment propertyAssessCheck1 = assessments1.getAssessment("9999330");
        assertNotNull(propertyAssessCheck1);
        assertEquals(9999330, propertyAssessCheck1.getAccountNumber());
        assertEquals("1713 ROBERTSON PLACE SW", propertyAssessCheck1.getHouse().getAddress().trim());
        assertEquals("Y", propertyAssessCheck1.getHouse().hasGarage());
        assertEquals(5454, propertyAssessCheck1.getNeighborhood().getNeighborhoodID());
        assertEquals("RUTHERFORD (Ipiihkoohkanipiaohtsi Ward)", propertyAssessCheck1.getNeighborhood().getNeighborWardString());
        assertEquals(481500, propertyAssessCheck1.getAssessedValue());
        assertEquals("(53.4205156452796, -113.53161625491973)", propertyAssessCheck1.getLocation().getLatLongString());
        assertArrayEquals(new String[]{"RESIDENTIAL 100%"}, propertyAssessCheck1.getAssessment().getAssessmentCollection());


        assertEquals(assessments1.getAssessment("9999330"), assessments2.getAssessment("9999330"));
    }

    @Test
    void containsAssessment() {
        assertEquals(true, assessments1.containsAssessment(9999330));
        assertEquals(assessments1.containsAssessment(9999330), assessments2.containsAssessment(9999330));
    }

    @Test
    void size() {
        assertEquals(2, assessments1.size());
    }

    @Test
    void AllAssessments() {
        PropertyAssessments allAssessments = assessments1.AllAssessments();
        assertNotNull(allAssessments);
        assertEquals(2, allAssessments.size());

        assertNotNull(allAssessments.getAssessment("9999330"));
        assertNotNull(allAssessments.getAssessment("10885155"));
    }

    @Test
    void neighbourhoodAssessments() {
        PropertyAssessments neighborhoodAssess = assessments1.NeighbourhoodAssessments("EDMONTON SOUTH EAST");
        assertEquals(1, neighborhoodAssess.size());

        assertEquals("EDMONTON SOUTH EAST", neighborhoodAssess.getAssessment("10885155").getNeighborhood().getNeighborhoodName());

        PropertyAssessments neighborhoodAssessNA = assessments1.NeighbourhoodAssessments("NO NEIGHBORHOOD");
        assertEquals(0, neighborhoodAssessNA.size());

    }

    @Test
    void getAssessmentsByAssessmentClass() {
        PropertyAssessments assessmentClassAssess = assessments1.getAssessmentsByAssessmentClass("RESIDENTIAL");
        assertNotNull(assessmentClassAssess);
        assertEquals(2, assessmentClassAssess.size());

        assertNotNull(assessmentClassAssess.getAssessment("10885155").getAssessment());

        PropertyAssessments assessmentClassNull = assessments1.getAssessmentsByAssessmentClass("COMMERCIAL");
        assertEquals(0, assessmentClassNull.size());

    }



    @Test
    void testInvalid(){
        assertThrows(IllegalArgumentException.class, () -> assessments1.getAssessment("-1"));

        assertThrows(IOException.class, () ->assessments1.addAssessment(new String[]{"","","1713",
                "ROBERTSON PLACE SW", "Y","5454","RUTHERFORD","Ipiihkoohkanipiaohtsi Ward","481500",
                "53.4205156452796","-113.53161625491973","POINT (-113.53161625491973 53.4205156452796)",
                "100","","","RESIDENTIAL","",""}));

        // account number is not a num
        assertThrows(NumberFormatException.class, () ->assessments1.addAssessment(new String[]{"not_int","","1713",
                "ROBERTSON PLACE SW", "Y","5454","RUTHERFORD","Ipiihkoohkanipiaohtsi Ward","481500",
                "53.4205156452796","-113.53161625491973","POINT (-113.53161625491973 53.4205156452796)",
                "100","","","RESIDENTIAL","",""}));

        // neighborhood ID is not a num
        assertThrows(NumberFormatException.class, () ->assessments1.addAssessment(new String[]{"99993300","","1713",
                "ROBERTSON PLACE SW", "Y","notInt","RUTHERFORD","Ipiihkoohkanipiaohtsi Ward","481500",
                "53.4205156452796","-113.53161625491973","POINT (-113.53161625491973 53.4205156452796)",
                "100","","","RESIDENTIAL","",""}));

        // assessed value is not a num
        assertThrows(NumberFormatException.class, () ->assessments1.addAssessment(new String[]{"99993300","","1713",
                "ROBERTSON PLACE SW", "Y","5454","RUTHERFORD","Ipiihkoohkanipiaohtsi Ward","notNum",
                "53.4205156452796","-113.53161625491973","POINT (-113.53161625491973 53.4205156452796)",
                "100","","","RESIDENTIAL","",""}));
        
        // assessment percentage is not a num
        assertThrows(NumberFormatException.class, () ->assessments1.addAssessment(new String[]{"99993300","","1713",
                "ROBERTSON PLACE SW", "Y","5454","RUTHERFORD","Ipiihkoohkanipiaohtsi Ward","481500",
                "53.4205156452796","-113.53161625491973","POINT (-113.53161625491973 53.4205156452796)",
                "notNum","","","RESIDENTIAL","",""}));

        // not a big enough array
        assertThrows(ArrayIndexOutOfBoundsException.class, () ->assessments1.addAssessment(new String[]{"99993300","1713",}));
        
        // account number is not a num
        assertThrows(NumberFormatException.class, () -> assessments1.getAssessment("notNum"));
        
        // not an account number
        assertThrows(NoSuchElementException.class, () -> assessments1.getAssessment("90"));
    }

    @Test
    void testEmpty() throws IOException {
        PropertyAssessments empty = new PropertyAssessments(new ArrayList<>());
        assertEquals(0, empty.getCount());
        assertEquals(0, empty.getMeanAssessedValue());
        assertEquals(0, empty.getMedianAssessedValue());
        assertEquals(Double.NEGATIVE_INFINITY, empty.maxAssessedValue());
        assertEquals(Double.POSITIVE_INFINITY, empty.minAssessedValue());

        // is "" for assessment class when is blank
        assessments1.addAssessment(new String[]{"99993300", "", "1713",
                "ROBERTSON PLACE SW", "Y", "5454", "RUTHERFORD", "Ipiihkoohkanipiaohtsi Ward", "481500",
                "53.4205156452796", "-113.53161625491973", "POINT (-113.53161625491973 53.4205156452796)",
                "", "", "", "", "", ""});

        assertEquals("", assessments1.getAssessment("99993300").getAssessment().getAssessmentTypes()[0]);

        // test if address is blank
        assessments1.addAssessment(new String[]{"99993300", "", "",
                "", "Y", "5454", "RUTHERFORD", "Ipiihkoohkanipiaohtsi Ward", "481500",
                "53.4205156452796", "-113.53161625491973", "POINT (-113.53161625491973 53.4205156452796)",
                "100", "", "", "RESIDENTIAL", "", ""});

        assertEquals("", assessments1.getAssessment("99993300").getHouse().getAddress());

        // test if garage is blank
        assessments1.addAssessment(new String[]{"99993300","","1713",
                "ROBERTSON PLACE SW", "","5454","RUTHERFORD","Ipiihkoohkanipiaohtsi Ward","481500",
                "53.4205156452796","-113.53161625491973","POINT (-113.53161625491973 53.4205156452796)",
                "100", "", "", "RESIDENTIAL", "", ""});

        assertEquals("", assessments1.getAssessment("99993300").getHouse().hasGarage());

        // test if neighborhoodName is blank
        assessments1.addAssessment(new String[]{"99993300","","1713",
                "ROBERTSON PLACE SW", "Y","5454","","Ipiihkoohkanipiaohtsi Ward","481500",
                "53.4205156452796","-113.53161625491973","POINT (-113.53161625491973 53.4205156452796)",
                "100", "", "", "RESIDENTIAL", "", ""});

        assertEquals("", assessments1.getAssessment("99993300").getNeighborhood().getNeighborhoodName());

        // test if ward is blank
        assessments1.addAssessment(new String[]{"99993300","","1713",
                "ROBERTSON PLACE SW", "Y","5454","RUTHERFORD","","481500",
                "53.4205156452796","-113.53161625491973","POINT (-113.53161625491973 53.4205156452796)",
                "100", "", "", "RESIDENTIAL", "", ""});

        assertEquals("", assessments1.getAssessment("99993300").getNeighborhood().getWard());

        // test if neighborhoodID is blank
        assessments1.addAssessment(new String[]{"99993300","","1713",
                "ROBERTSON PLACE SW", "Y","","RUTHERFORD","Ipiihkoohkanipiaohtsi Ward","481500",
                "53.4205156452796","-113.53161625491973","POINT (-113.53161625491973 53.4205156452796)",
                "100", "", "", "RESIDENTIAL", "", ""});

        assertEquals(-1, assessments1.getAssessment("99993300").getNeighborhood().getNeighborhoodID());


        // test if latitude is blank
        assessments1.addAssessment(new String[]{"99993300","","1713",
                "ROBERTSON PLACE SW", "Y","5454","RUTHERFORD","Ipiihkoohkanipiaohtsi Ward","481500",
                "","-113.53161625491973","POINT (-113.53161625491973 53.4205156452796)",
                "100", "", "", "RESIDENTIAL", "", ""});

        assertEquals(BigDecimal.ZERO, assessments1.getAssessment("99993300").getLocation().getLatitude());

        // test if longitude is blank
        assessments1.addAssessment(new String[]{"99993300","","1713",
                "ROBERTSON PLACE SW", "Y","5454","RUTHERFORD","Ipiihkoohkanipiaohtsi Ward","481500",
                "53.4205156452796","","POINT (-113.53161625491973 53.4205156452796)",
                "100", "", "", "RESIDENTIAL", "", ""});

        assertEquals(BigDecimal.ZERO, assessments1.getAssessment("99993300").getLocation().getLongitude());

        // test if point is blank
        assessments1.addAssessment(new String[]{"99993300","","1713",
                "ROBERTSON PLACE SW", "Y","5454","RUTHERFORD","Ipiihkoohkanipiaohtsi Ward","481500",
                "53.4205156452796","-113.53161625491973","",
                "100", "", "", "RESIDENTIAL", "", ""});

        assertEquals("", assessments1.getAssessment("99993300").getLocation().getPointLocation());

        // test if accountNumber is blank
//        assessments1.addAssessment(new String[]{"","","1713",
//                "ROBERTSON PLACE SW", "Y","5454","RUTHERFORD","Ipiihkoohkanipiaohtsi Ward","481500",
//                "53.4205156452796","-113.53161625491973","POINT (-113.53161625491973 53.4205156452796)",
//                "100", "", "", "RESIDENTIAL", "", ""});

//        assertEquals(-1, assessments1.getAssessment("99993300").getAccountNumber());

        // test if assessedValue is blank
        assessments1.addAssessment(new String[]{"99993300","","1713",
                "ROBERTSON PLACE SW", "Y","5454","RUTHERFORD","Ipiihkoohkanipiaohtsi Ward","",
                "53.4205156452796","-113.53161625491973","POINT (-113.53161625491973 53.4205156452796)",
                "100", "", "", "RESIDENTIAL", "", ""});

        assertEquals(-1, assessments1.getAssessment("99993300").getAssessedValue());


    }

        @Test
    void testMedianOddLength() throws IOException {
        PropertyAssessments medianOddLength = new PropertyAssessments("test_input_different.csv");
        assertEquals(494500, medianOddLength.getMedianAssessedValue());
    }
    @Test
    void testFileReader(){
        assertThrows(IOException.class, () -> new PropertyAssessments("not_a_file.csv"));
    }
    @Test
    void testEquals() throws IOException {
        // reflexive
        assertEquals(assessments1, assessments1);

        // symmetric
        assertEquals(assessments1.equals(assessments2), assessments2.equals(assessments1));

        PropertyAssessments assessments3 = new PropertyAssessments("test_input.csv");

        // transitive
        if (assessments1.equals(assessments2) && assessments2.equals(assessments3)) {
            assertEquals(assessments1, assessments3);
        }

        assertNotEquals(assessments1, null);
        assertNotEquals(assessments1, "string");

        // Test with different data
        PropertyAssessments different = new PropertyAssessments("test_input_different.csv");
        assertNotEquals(assessments1, different);

        // Test equal collections
        assertEquals(assessments1, assessments2); // Both loaded from same file
    }

    @Test
    void hashCodeTest() {
        assertEquals(assessments1.hashCode(), assessments2.hashCode());

        assertEquals(assessments1.hashCode(), assessments1.hashCode());
    }
}