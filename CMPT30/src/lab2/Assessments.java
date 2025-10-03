package lab2;

import java.util.Arrays;

public class Assessments{
    private final int[] assessmentPercentages;
    private final String[] assessmentTypes;

    public Assessments(int[] assessmentPercentages, String[] assessmentTypes){
        this.assessmentPercentages = assessmentPercentages;
        this.assessmentTypes = assessmentTypes;
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