package lab2;

public class House{
    private final String suite;
    private final String houseNumber;
    private final String streetName;
    private final String garage;

    public House(String suite, String houseNumber, String streetName, String garage){
        this.suite = suite;
        this.houseNumber = houseNumber;
        this.streetName = streetName;
        this.garage = garage;
    }

    public String getAddress(){
        return suite.trim() + " " + houseNumber.trim() + " " + streetName.trim();
    }

    public String hasGarage(){
        return garage;
    }

}