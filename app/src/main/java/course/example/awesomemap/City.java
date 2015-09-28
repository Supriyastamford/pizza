package course.example.awesomemap;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class City {
    public String name;
    public double lat;
    public double lng;
    public LatLng latLng;
    public Marker marker;

    public City(String name, double lat, double lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.latLng = new LatLng(lat, lng);
    }

    public String toString() {
        return name;
    }
}
