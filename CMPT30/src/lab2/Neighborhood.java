package lab2;

import java.util.Objects;

public class Neighborhood{
    private final int neighborhoodID;
    private final String neighborhoodName;
    private final String ward;

    public Neighborhood(int neighborhoodID, String neighborhoodName, String ward){
        if (neighborhoodID < -1) {
            throw new IllegalArgumentException("Neighborhood ID is invalid");
        }
        this.neighborhoodID = neighborhoodID;
        this.neighborhoodName = neighborhoodName;
        this.ward = ward;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Neighborhood that = (Neighborhood) o;
        return neighborhoodID == that.neighborhoodID &&
                Objects.equals(neighborhoodName, that.neighborhoodName) &&
                Objects.equals(ward, that.ward);
    }

    @Override
    public int hashCode() {
        return Objects.hash(neighborhoodID, neighborhoodName, ward);
    }

    public int getNeighborhoodID(){
        return neighborhoodID;
    }
    public String getNeighborhoodName(){
        return neighborhoodName;
    }
    public String getWard(){
        return ward;
    }
    public String getNeighborWardString(){
        return neighborhoodName + " (" + ward + ")";
    }

}
