package lab2;

public class Neighborhood{
    private final int neighborhoodID;
    private final String neighborhoodName;
    private final String ward;

    public Neighborhood(int neighborhoodID, String neighborhoodName, String ward){
        this.neighborhoodID = neighborhoodID;
        this.neighborhoodName = neighborhoodName;
        this.ward = ward;
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
