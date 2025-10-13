import lab2.Assessments;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

//10885158		6425	34 STREET SW	Y	8886	EDMONTON SOUTH EAST	Sspomitapi Ward	548500	53.37587356002650	-113.37512500000000	POINT (-113.37512496283806 53.375873560026506)	91	3	3	RESIDENTIAL	FARMLAND	RESIDENTIAL
//10009278	415	10403	98 AVENUE NW	N	1090	DOWNTOWN	O-day'min Ward	236000	53.53520125659900	-113.49977200000000	POINT (-113.49977198768597 53.535201256599045)	100			RESIDENTIAL

class AssessmentsTest {

    private Assessments assessments1;
    private Assessments assessment1Copy;
    private Assessments assessments2;

    @BeforeEach
    void setUp() {
        assessments1 = new Assessments(new int[]{91, 3, 3}, new String[]{"RESIDENTIAL", "FARMLAND", "RESIDENTIAL"});
        assessment1Copy = new Assessments(new int[]{91, 3, 3}, new String[]{"RESIDENTIAL", "FARMLAND", "RESIDENTIAL"});
        assessments2 = new Assessments(new int[]{100}, new String[]{"RESIDENTIAL"});
    }

    @Test
    void getAssessmentPercentages() {
        assertArrayEquals(new int[]{91, 3, 3}, assessments1.getAssessmentPercentages());
        assertFalse(Arrays.equals(new int[]{91, 3, 3}, assessments2.getAssessmentPercentages()));
    }

    @Test
    void getAssessmentTypes() {
        assertArrayEquals(new String[]{"RESIDENTIAL", "FARMLAND", "RESIDENTIAL"}, assessments1.getAssessmentTypes());
        assertFalse(Arrays.equals(new String[]{"RESIDENTIAL", "FARMLAND", "RESIDENTIAL"}, assessments2.getAssessmentTypes()));
    }

    @Test
    void getAssessmentCollection() {
        assertArrayEquals(new String[]{"RESIDENTIAL 91%", "FARMLAND 3%", "RESIDENTIAL 3%"}, assessments1.getAssessmentCollection());
        assertFalse(Arrays.equals(new String[]{"RESIDENTIAL 91%", "FARMLAND 3%", "RESIDENTIAL 3%"}, assessments2.getAssessmentCollection()));
    }

    @Test
    void testInvalid(){
        assertThrows(IllegalArgumentException.class, () -> new Assessments(new int[]{91}, new String[]{"RESIDENTIAL", "FARMLAND", "RESIDENTIAL"}));

        assertThrows(IllegalArgumentException.class, () -> new Assessments(new int[]{91, 5, 4}, new String[]{"RESIDENTIAL"}));

    }

    @Test
    void testNullAssessmentType(){
        Assessments nullAssessments = new Assessments(new int[]{91, 3, 3}, new String[]{"RESIDENTIAL", null,  "RESIDENTIAL"});

        assertEquals(2, nullAssessments.getAssessmentCollection().length);
    }

    @Test
    void testNegativeAssessmentPercentage(){
        Assessments negAssessments = new Assessments(new int[]{-91, 3, 3}, new String[]{"RESIDENTIAL", "COMMERCIAL",  "RESIDENTIAL"});

        assertEquals(2, negAssessments.getAssessmentCollection().length);
    }

    @Test
    void testEmpty(){
        Assessments empty = new Assessments(new int[]{0, 0, 0}, new String[]{"", "", ""});
        //assertEquals(0, empty.getAssessmentTypes().length);
        //assertEquals(0, empty.getAssessmentCollection().length);
    }

    @Test
    void equals(){
        //reflexive
        assertEquals(assessments1, assessments1);

        // symmetric
        assertEquals(assessments1.equals(assessment1Copy), assessment1Copy.equals(assessments1));
        assertEquals(assessments1.equals(assessments2), assessments2.equals(assessments1));

        //transitive
        Assessments assessment1Copy2 = new Assessments(new int[]{91, 3, 3}, new String[]{"RESIDENTIAL", "FARMLAND", "RESIDENTIAL"});
        if (assessments1.equals(assessment1Copy) && assessment1Copy.equals(assessment1Copy2))  {
            assertEquals(assessments1, assessment1Copy2);
        }
        if (!assessments2.equals(assessment1Copy) && assessment1Copy.equals(assessment1Copy2))  {
            assertNotEquals(assessments2, assessment1Copy2);
        }

        // false results
        assertNotEquals(assessments1, null);
        assertNotEquals(assessments1, "string");

        // test all branches
        assertNotEquals( new Assessments(new int[]{95, 5}, new String[]{"RESIDENTIAL", "FARMLAND"}), new Assessments(new int[]{100}, new String[]{"FARMLAND"}));
        assertNotEquals( new Assessments(new int[]{95, 5}, new String[]{"RESIDENTIAL", "FARMLAND"}), new Assessments(new int[]{25, 75}, new String[]{"RESIDENTIAL", "FARMLAND"}));
        assertNotEquals( new Assessments(new int[]{95, 5}, new String[]{"RESIDENTIAL", "FARMLAND"}), new Assessments(new int[]{66,10,23}, new String[]{"FARMLAND", "RESIDENTIAL", "COMMERCIAL"}));

    }

    @Test
    void testHashCode(){
        assertEquals(assessments1.hashCode(), assessment1Copy.hashCode());
    }


}