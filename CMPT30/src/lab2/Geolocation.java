package lab2;

import java.math.BigDecimal;

public class Geolocation{
    private final BigDecimal latitude;
    private final BigDecimal longitude;
    private final String pointLocation;

    public Geolocation(BigDecimal latitude, BigDecimal longitude, String pointLocation){
        this.latitude = latitude;
        this.longitude = longitude;
        this.pointLocation = pointLocation;
    }
    public BigDecimal getLatitude(){
        return latitude;
    }
    public BigDecimal getLongitude(){
        return longitude;
    }
    public String getPointLocation(){
        return pointLocation;
    }
    public String getLatLongString(){
        return "(" + latitude.doubleValue() + ", " + longitude.doubleValue() + ")";
    }
}