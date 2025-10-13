package lab2;

import java.util.Arrays;
import java.util.Objects;

public class Assessments{
    private final int[] assessmentPercentages;
    private final String[] assessmentTypes;

    public Assessments(int[] assessmentPercentages, String[] assessmentTypes){
        if (assessmentPercentages.length != assessmentTypes.length){
            throw new IllegalArgumentException("Number of assessment percentages and assessment types must be equal");
        }
        this.assessmentPercentages = assessmentPercentages;
        this.assessmentTypes = assessmentTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Assessments that = (Assessments) o;
        return Arrays.equals(assessmentPercentages, that.assessmentPercentages) && Arrays.equals(assessmentTypes, that.assessmentTypes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(assessmentPercentages), Arrays.hashCode(assessmentTypes));
    }

    public int[] getAssessmentPercentages(){
        return assessmentPercentages;
    }
    public String[] getAssessmentTypes(){
        return assessmentTypes;
    }

    public String[] getAssessmentCollection(){
        String[] assessmentClasses = new String[assessmentTypes.length];
        int length = 0;
        for(int i = 0; i < assessmentClasses.length; i++){
            if (assessmentTypes[i] != null && assessmentPercentages[i] > -1){
                assessmentClasses[i] = assessmentTypes[i] + " " + assessmentPercentages[i] + "%";
                length++;
            }
        }
        return Arrays.copyOf(assessmentClasses, length);
    }
}