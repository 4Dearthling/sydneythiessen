import lab2.Geolocation;
import lab2.Neighborhood;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
//10885158		6425	34 STREET SW	Y	8886	EDMONTON SOUTH EAST	Sspomitapi Ward	548500	53.37587356002650	-113.37512500000000	POINT (-113.37512496283806 53.375873560026506)	91	3	3	RESIDENTIAL	FARMLAND	RESIDENTIAL
//10009278	415	10403	98 AVENUE NW	N	1090	DOWNTOWN	O-day'min Ward	236000	53.53520125659900	-113.49977200000000	POINT (-113.49977198768597 53.535201256599045)	100			RESIDENTIAL

class NeighborhoodTest {
    private Neighborhood neighborhood1;
    private Neighborhood neighborhood1Copy;
    private Neighborhood neighborhood2;

    @BeforeEach
    void setUp() {
        neighborhood1 = new Neighborhood(8886, "EDMONTON SOUTH EAST", "Sspomitapi Ward");
        neighborhood1Copy = new Neighborhood(8886, "EDMONTON SOUTH EAST", "Sspomitapi Ward");
        neighborhood2 = new Neighborhood(1090, "DOWNTOWN", "O-day'min Ward");
    }

    @Test
    void getNeighborhoodID() {
        assertEquals(8886, neighborhood1.getNeighborhoodID());
        assertNotEquals(8886, neighborhood2.getNeighborhoodID());
    }

    @Test
    void getNeighborhoodName() {
        assertEquals("EDMONTON SOUTH EAST", neighborhood1.getNeighborhoodName());
        assertNotEquals("EDMONTON SOUTH EAST", neighborhood2.getNeighborhoodName());
    }

    @Test
    void getWard() {
        assertEquals("Sspomitapi Ward", neighborhood1.getWard());
        assertNotEquals("Sspomitapi Ward", neighborhood2.getWard());
    }

    @Test
    void getNeighborWardString() {
        assertEquals("EDMONTON SOUTH EAST (Sspomitapi Ward)", neighborhood1.getNeighborWardString());

    }

    @Test
    void testInvalidNeighborhoodID() {
        assertThrows(IllegalArgumentException.class, () -> new Neighborhood(-100, "DOWNTOWN", "O-day'min Ward"));
    }

    @Test
    void testEquals() {
        // reflexive
        assertEquals(neighborhood1, neighborhood1);

        // symmetric
        assertEquals(neighborhood1.equals(neighborhood1Copy), neighborhood1Copy.equals(neighborhood1));
        assertEquals(neighborhood1.equals(neighborhood2), neighborhood2.equals(neighborhood1));

        // transitive
        Neighborhood neighborhood1Copy2 = new Neighborhood(8886, "EDMONTON SOUTH EAST", "Sspomitapi Ward");
        if (neighborhood1.equals(neighborhood1Copy) && neighborhood1Copy.equals(neighborhood1Copy2)) {
            assertEquals(neighborhood1, neighborhood1Copy2);
        }
        if (!neighborhood2.equals(neighborhood1Copy) && neighborhood1Copy.equals(neighborhood1Copy2)) {
            assertNotEquals(neighborhood2, neighborhood1Copy2);
        }

        assertNotEquals(neighborhood1, null);  // x.equals(null) should return false.
        assertNotEquals(neighborhood1, "string");  // incorrect type

        // test all branches of the compound return statement of equals (uses shortcutting):
        assertNotEquals(neighborhood1, new Neighborhood(9999, "EDMONTON SOUTH EAST", "Sspomitapi Ward"));

        assertNotEquals(neighborhood1, new Neighborhood(8886, "DIFFERENT NAME", "Sspomitapi Ward"));

        assertNotEquals(neighborhood1, new Neighborhood(8886, "EDMONTON SOUTH EAST", "Different Ward"));
    }

    @Test
    void hashCodeTest() {
        // Equal objects must have equal hash codes
        assertEquals(neighborhood1.hashCode(), neighborhood1Copy.hashCode());

        assertNotEquals(neighborhood1.hashCode(), neighborhood2.hashCode());
    }
}