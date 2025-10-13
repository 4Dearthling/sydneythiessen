import lab2.House;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//10885158		6425	34 STREET SW	Y	8886	EDMONTON SOUTH EAST	Sspomitapi Ward	548500	53.37587356002650	-113.37512500000000	POINT (-113.37512496283806 53.375873560026506)	91	3	3	RESIDENTIAL	FARMLAND	RESIDENTIAL
//10009278	415	10403	98 AVENUE NW	N	1090	DOWNTOWN	O-day'min Ward	236000	53.53520125659900	-113.49977200000000	POINT (-113.49977198768597 53.535201256599045)	100			RESIDENTIAL

class HouseTest {
    private House house1;
    private House house1Copy;
    private House house2;

    @BeforeEach
    void setUp() {
        house1 = new House("415", "10403", "98 Avenue NW", "N");
        house1Copy = new House("415", "10403", "98 Avenue NW", "N");
        house2 = new House("", "6425", "34 STREET SW", "Y");
    }

    @Test
    void getAddress() {
        assertEquals("415 10403 98 Avenue NW", house1.getAddress());
        assertNotEquals("415 10403 98 Avenue NW", house2.getAddress());
    }

    @Test
    void hasGarage() {
        assertEquals("N", house1.hasGarage());
        assertNotEquals("N", house2.hasGarage());
    }

    @Test
    void testInvalid(){
        assertThrows(IllegalArgumentException.class, () -> new House("-10", "10403", "98 Avenue NW", "N"));
        assertThrows(IllegalArgumentException.class, () -> new House("", "-10403", "98 Avenue NW", "N") );
    }

    @Test
    void testEquals() {
        // reflexive
        assertEquals(house1, house1);

        // symmetric
        assertEquals(house1.equals(house1Copy), house1Copy.equals(house1));
        assertEquals(house1.equals(house2), house2.equals(house1));

        // transitive
        House house1Copy2 = new House("415", "10403", "98 Avenue NW", "N");
        if (house1.equals(house1Copy) && house1Copy.equals(house1Copy2)) {
            assertEquals(house1, house1Copy2);
        }
        if (!house2.equals(house1Copy) && house1Copy.equals(house1Copy2)) {
            assertNotEquals(house2, house1Copy2);
        }

        assertNotEquals(house1, null);  // x.equals(null) should return false.
        assertNotEquals(house1, "string");  // incorrect type

        // Different suite
        assertNotEquals(house1, new House("999", "10403", "98 Avenue NW", "N"));

        // Different houseNumber
        assertNotEquals(house1, new House("415", "9999", "98 Avenue NW", "N"));

        // Different streetName
        assertNotEquals(house1, new House("415", "10403", "Different Street", "N"));

        // Different garage
        assertNotEquals(house1, new House("415", "10403", "98 Avenue NW", "Y"));
    }

    @Test
    void hashCodeTest() {
        // Equal objects must have equal hash codes
        assertEquals(house1.hashCode(), house1Copy.hashCode());

        // Different objects likely have different hash codes (not guaranteed, but highly probable)
        assertNotEquals(house1.hashCode(), house2.hashCode());
    }


}