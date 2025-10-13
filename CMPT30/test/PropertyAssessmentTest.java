import lab2.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PropertyAssessmentTest {

    private PropertyAssessment property1;
    private PropertyAssessment property1Copy;
    private PropertyAssessment property2;

    @BeforeEach
    void setUp() {
        House house1 = new House("", "1713", "ROBERTSON PLACE SW", "Y");
        Neighborhood neighborhood1 = new Neighborhood(5454, "RUTHERFORD", "Ipiihkoohkanipiaohtsi Ward");
        Geolocation location1 = new Geolocation(
                new BigDecimal("53.4205156452796"),
                new BigDecimal("-113.53161625491973"),
                "POINT (-113.53161625491973 53.4205156452796)"
        );
        Assessments assessment1 = new Assessments(new int[]{100}, new String[]{"RESIDENTIAL"});
        property1 = new PropertyAssessment(9999330, house1, neighborhood1, 481500, location1, assessment1);

        House house2 = new House("", "4325", "34 STREET SW", "Y");
        Neighborhood neighborhood2 = new Neighborhood(8886, "EDMONTON SOUTH EAST", "Sspomitapi Ward");
        Geolocation location2 = new Geolocation(
                new BigDecimal("53.39215985330776"),
                new BigDecimal("-113.3903186"),
                "POINT (-113.39031863530188 53.39215985330776)"
        );
        property1Copy = new PropertyAssessment(9999330, house1, neighborhood1, 481500, location1, assessment1);

        Assessments assessment2 = new Assessments(new int[]{95, 3, 2}, new String[]{"RESIDENTIAL", "RESIDENTIAL", "FARMLAND"});
        property2 = new PropertyAssessment(10885155, house2, neighborhood2, 613000, location2, assessment2);

    }

    @Test
    void compareTo() {
        assertTrue(property1.compareTo(property2) < 0);

        assertTrue(property2.compareTo(property1) > 0);

        assertEquals(0, property1.compareTo(property1Copy));

        assertEquals(0, property1.compareTo(property1));
    }

    @Test
    void getAccountNumber() {
        assertEquals(9999330, property1.getAccountNumber());
        assertEquals(10885155, property2.getAccountNumber());
    }

    @Test
    void getAssessedValue() {
        assertEquals(481500, property1.getAssessedValue(), 0.01);
        assertEquals(613000, property2.getAssessedValue(), 0.01);
    }

    @Test
    void getHouse() {
        assertNotNull(property1.getHouse());
        assertEquals("1713 ROBERTSON PLACE SW", property1.getHouse().getAddress());

        assertNotNull(property2.getHouse());
        assertEquals("4325 34 STREET SW", property2.getHouse().getAddress());
    }

    @Test
    void getNeighborhood() {
        assertNotNull(property1.getNeighborhood());
        assertEquals("RUTHERFORD", property1.getNeighborhood().getNeighborhoodName());
        assertEquals(5454, property1.getNeighborhood().getNeighborhoodID());

        assertNotNull(property2.getNeighborhood());
        assertEquals("EDMONTON SOUTH EAST", property2.getNeighborhood().getNeighborhoodName());
        assertEquals(8886, property2.getNeighborhood().getNeighborhoodID());
    }

    @Test
    void getLocation() {
        assertNotNull(property1.getLocation());
        assertEquals("(53.4205156452796, -113.53161625491973)", property1.getLocation().getLatLongString());

        assertNotNull(property2.getLocation());
        assertEquals("(53.39215985330776, -113.3903186)", property2.getLocation().getLatLongString());
    }

    @Test
    void getAssessment() {
        assertNotNull(property1.getAssessment());
        assertArrayEquals(new int[]{100}, property1.getAssessment().getAssessmentPercentages());
        assertArrayEquals(new String[]{"RESIDENTIAL"}, property1.getAssessment().getAssessmentTypes());

        assertNotNull(property2.getAssessment());
        assertArrayEquals(new int[]{95, 3, 2}, property2.getAssessment().getAssessmentPercentages());
        assertArrayEquals(new String[]{"RESIDENTIAL", "RESIDENTIAL", "FARMLAND"}, property2.getAssessment().getAssessmentTypes());
    }

    @Test
    void testEquals() {
        // reflexive
        assertEquals(property1, property1);

        // symmetric
        assertEquals(property1.equals(property1Copy), property1Copy.equals(property1));
        assertEquals(property1.equals(property2), property2.equals(property1));

        // transitive
        House house1Copy = new House("", "1713", "ROBERTSON PLACE SW", "Y");
        Neighborhood neighborhood1Copy = new Neighborhood(5454, "RUTHERFORD", "Ipiihkoohkanipiaohtsi Ward");
        Geolocation location1Copy = new Geolocation(
                new BigDecimal("53.4205156452796"),
                new BigDecimal("-113.53161625491973"),
                "POINT (-113.53161625491973 53.4205156452796)"
        );
        Assessments assessment1Copy = new Assessments(new int[]{100}, new String[]{"RESIDENTIAL"});
        PropertyAssessment property1Copy2 = new PropertyAssessment(9999330, house1Copy, neighborhood1Copy, 481500, location1Copy, assessment1Copy);

        if (property1.equals(property1Copy) && property1Copy.equals(property1Copy2)) {
            assertEquals(property1, property1Copy2);
        }
        if (!property2.equals(property1Copy) && property1Copy.equals(property1Copy2)) {
            assertNotEquals(property2, property1Copy2);
        }

        // false results
        assertNotEquals(property1, null);  // x.equals(null) should return false.
        assertNotEquals(property1, "string");  // incorrect type

        // test all branches of the compound return statement of equals (uses shortcutting):
        House houseTest = new House("", "1713", "ROBERTSON PLACE SW", "Y");
        Neighborhood neighborhoodTest = new Neighborhood(5454, "RUTHERFORD", "Ipiihkoohkanipiaohtsi Ward");
        Geolocation locationTest = new Geolocation(
                new BigDecimal("53.4205156452796"),
                new BigDecimal("-113.53161625491973"),
                "POINT (-113.53161625491973 53.4205156452796)"
        );
        Assessments assessmentTest = new Assessments(new int[]{100}, new String[]{"RESIDENTIAL"});

        assertNotEquals(property1, new PropertyAssessment(1111111, houseTest, neighborhoodTest, 481500, locationTest, assessmentTest));

        assertNotEquals(property1, new PropertyAssessment(9999330, houseTest, neighborhoodTest, 999999, locationTest, assessmentTest));

        House houseDifferent = new House("", "9999", "DIFFERENT ST", "N");
        assertNotEquals(property1, new PropertyAssessment(9999330, houseDifferent, neighborhoodTest, 481500, locationTest, assessmentTest));

        Neighborhood neighborhoodDifferent = new Neighborhood(1234, "DIFFERENT NEIGHBORHOOD", "Different Ward");
        assertNotEquals(property1, new PropertyAssessment(9999330, houseTest, neighborhoodDifferent, 481500, locationTest, assessmentTest));


        Geolocation locationDifferent = new Geolocation(
                new BigDecimal("50"),
                new BigDecimal("-50"),
                "POINT (-50, 50)"
        );
        assertNotEquals(property1, new PropertyAssessment(9999330, houseTest, neighborhoodTest, 481500, locationDifferent, assessmentTest));
        Assessments assessmentDifferent = new Assessments(new int[]{50, 50}, new String[]{"COMMERCIAL", "RESIDENTIAL"});
        assertNotEquals(property1, new PropertyAssessment(9999330, houseTest, neighborhoodTest, 481500, locationTest, assessmentDifferent));
    }

    @Test
    void hashCodeTest() {
        // Equal objects must have equal hash codes
        assertEquals(property1.hashCode(), property1Copy.hashCode());

        // Different objects likely have different hash codes (not guaranteed, but highly probable)
        assertNotEquals(property1.hashCode(), property2.hashCode());

    }

}