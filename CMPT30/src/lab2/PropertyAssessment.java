package lab2;

import java.math.BigDecimal;
import java.util.Arrays;

public class PropertyAssessment implements Comparable<PropertyAssessment> {
    private double accountNumber;

    private House house;
    private Neighborhood neighborhood;
    private Geolocation location;
    private Assessments assessment;

    private double assessedValue;



    public PropertyAssessment(double accountNumber, House house, Neighborhood neighborhood, double assessedValue,
                              Geolocation location, Assessments assessment) {
        this.accountNumber = accountNumber;
        this.house = house;
        this.neighborhood = neighborhood;
        this.assessedValue = assessedValue;
        this.location = location;
        this.assessment = assessment;
    }

    @Override
    public int compareTo(PropertyAssessment o) {
        return Double.compare(this.assessedValue, o.assessedValue);
    }
    public int getAccountNumber() {
        return (int) accountNumber;
    }
    public double getAssessedValue(){
        return assessedValue;
    }
    public House getHouse() { return house; }
    public Neighborhood getNeighborhood() { return neighborhood; }
    public Geolocation getLocation() { return location; }
    public Assessments getAssessment() { return assessment; }
}



