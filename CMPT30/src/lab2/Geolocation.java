package lab2;

import java.math.BigDecimal;
import java.util.Objects;

public class Geolocation{
    private final BigDecimal latitude;
    private final BigDecimal longitude;
    private final String pointLocation;

    public Geolocation(BigDecimal latitude, BigDecimal longitude, String pointLocation){
        if (latitude.compareTo(new BigDecimal("-90")) < 0 || longitude.compareTo(new BigDecimal("-180")) < 0 || latitude.compareTo(new BigDecimal("90")) > 0 || longitude.compareTo(new BigDecimal("180")) > 0){
            throw new IllegalArgumentException("Latitude and longitude must be in range [-90, 90], [-180, 180]");
        }
        this.latitude = latitude;
        this.longitude = longitude;
        this.pointLocation = pointLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Geolocation that = (Geolocation) o;
        return Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude) &&
                Objects.equals(pointLocation, that.pointLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude, pointLocation);
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