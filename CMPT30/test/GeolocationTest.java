import lab2.Geolocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

//10885158		6425	34 STREET SW	Y	8886	EDMONTON SOUTH EAST	Sspomitapi Ward	548500	53.37587356002650	-113.37512500000000	POINT (-113.37512496283806 53.375873560026506)	91	3	3	RESIDENTIAL	FARMLAND	RESIDENTIAL
//10009278	415	10403	98 AVENUE NW	N	1090	DOWNTOWN	O-day'min Ward	236000	53.53520125659900	-113.49977200000000	POINT (-113.49977198768597 53.535201256599045)	100			RESIDENTIAL
class GeolocationTest {

    private Geolocation geo1;
    private Geolocation geo1Copy;
    private Geolocation geo2;

    @BeforeEach
    void setUp() {
        geo1 = new Geolocation(new BigDecimal("53.3758735600265"), new BigDecimal("-113.37512496283806"), "POINT (-113.37512496283806 53.375873560026506)");
        geo1Copy = new Geolocation(new BigDecimal("53.3758735600265"), new BigDecimal("-113.37512496283806"), "POINT (-113.37512496283806 53.375873560026506)");
        geo2 = new Geolocation(new BigDecimal("53.535201256599"), new BigDecimal("-113.49977198768597"), "POINT (-113.49977198768597 53.535201256599045)");


    }
    @Test
    void getLatitude() {
        assertEquals(new BigDecimal("53.3758735600265"), geo1.getLatitude());
        assertNotEquals(new BigDecimal("53.3758735600265"), geo2.getLatitude());
    }

    @Test
    void getLongitude() {
        assertEquals(new BigDecimal("-113.37512496283806"), geo1.getLongitude());
        assertNotEquals(new BigDecimal("-113.37512496283806"), geo2.getLongitude());
    }

    @Test
    void getPointLocation() {
        assertEquals("POINT (-113.37512496283806 53.375873560026506)", geo1.getPointLocation());
        assertNotEquals("POINT (-113.37512496283806 53.375873560026506)", geo2.getPointLocation());
    }

    @Test
    void getLatLongString() {
        assertEquals("(53.3758735600265, -113.37512496283806)", geo1.getLatLongString());
        assertNotEquals("(53.3758735600265, -113.37512496283806)", geo2.getLatLongString());

    }

    @Test
    void testInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Geolocation(new BigDecimal("-100"), new BigDecimal("-100"), "POINT (-100 -100)"));
        assertThrows(IllegalArgumentException.class, () -> new Geolocation(new BigDecimal("100"), new BigDecimal("100"), "POINT (100 100)"));
        assertThrows(IllegalArgumentException.class, () -> new Geolocation(new BigDecimal("-50"), new BigDecimal("-200"), "POINT (-50 -200)"));
        assertThrows(IllegalArgumentException.class, () -> new Geolocation(new BigDecimal("50"), new BigDecimal("200"), "POINT (50 200)"));

        assertThrows(IllegalArgumentException.class, () -> new Geolocation(new BigDecimal("-100"), new BigDecimal("-200"), "POINT (-100 -200)"));
        assertThrows(IllegalArgumentException.class, () -> new Geolocation(new BigDecimal("100"), new BigDecimal("200"), "POINT (100 200)"));
    }

    @Test
    void testEmpty(){

    }

    @Test
    void testEquals() {
        // ignore the IntelliJ warnings, it doesn't understand that we're testing the equals method.

        // reflexive
        assertEquals(geo1, geo1);

        // symmetric
        assertEquals(geo1.equals(geo1Copy), geo1Copy.equals(geo1));
        assertEquals(geo1.equals(geo2), geo2.equals(geo1));

        // transitive
        Geolocation geo1Copy2 = new Geolocation(new BigDecimal("53.3758735600265"), new BigDecimal("-113.37512496283806"), "POINT (-113.37512496283806 53.375873560026506)");
        if (geo1.equals(geo1Copy) && geo1Copy.equals(geo1Copy2)) {
            assertEquals(geo1, geo1Copy2);
        }
        if (!geo2.equals(geo1Copy) && geo1Copy.equals(geo1Copy2)) {
            assertNotEquals(geo2, geo1Copy2);
        }

        assertNotEquals(geo1, null);  // x.equals(null) should return false.
        assertNotEquals(geo1, "string");  // incorrect type

        // test all branches of the compound return statement of equals (uses shortcutting):
        assertNotEquals(geo1, new Geolocation(new BigDecimal("90"), new BigDecimal("-113.37512496283806"), "POINT (-113.37512496283806 53.375873560026506)"));

        assertNotEquals(geo1, new Geolocation(new BigDecimal("53.3758735600265"), new BigDecimal("-99.999"), "POINT (-113.37512496283806 53.375873560026506)"));

        assertNotEquals(geo1, new Geolocation(new BigDecimal("53.3758735600265"), new BigDecimal("-113.37512496283806"), "POINT (0 0)"));
    }

    @Test
    void hashCodeTest() {
        // Equal objects must have equal hash codes
        assertEquals(geo1.hashCode(), geo1Copy.hashCode());

        // Different objects likely have different hash codes (not guaranteed, but highly probable)
        assertNotEquals(geo1.hashCode(), geo2.hashCode());
    }
}