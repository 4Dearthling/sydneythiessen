package lab2;

import java.util.Objects;

public class House{
    private final String suite;
    private final String houseNumber;
    private final String streetName;
    private final String garage;

    public House(String suite, String houseNumber, String streetName, String garage){
        if (!Objects.equals(suite, "") && suite.compareTo("0") < 0 || !Objects.equals(houseNumber, "") && houseNumber.compareTo("0") < 0){
            throw new IllegalArgumentException("Suite or house number is invalid");
        }
        this.suite = suite;
        this.houseNumber = houseNumber;
        this.streetName = streetName;
        this.garage = garage;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Objects.equals(suite, house.suite) &&
                Objects.equals(houseNumber, house.houseNumber) &&
                Objects.equals(streetName, house.streetName) &&
                Objects.equals(garage, house.garage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suite, houseNumber, streetName, garage);
    }

    public String getAddress(){
        return (suite.trim() + " " + houseNumber.trim() + " " + streetName.trim()).trim();
    }

    public String hasGarage(){
        return garage;
    }

}