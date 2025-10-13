package lab2;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PropertyAssessment that = (PropertyAssessment) o;
        return Double.compare(accountNumber, that.accountNumber) == 0 &&
                Double.compare(assessedValue, that.assessedValue) == 0 &&
                Objects.equals(house, that.house) &&
                Objects.equals(neighborhood, that.neighborhood) &&
                Objects.equals(location, that.location) &&
                Objects.equals(assessment, that.assessment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, house, neighborhood, location, assessment, assessedValue);
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



