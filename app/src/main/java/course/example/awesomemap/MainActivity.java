package course.example.awesomemap;

import android.app.*;
import android.content.*;
import android.location.*;
import android.os.*;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import java.util.*;

public class MainActivity extends Activity
        implements OnMapReadyCallback, GoogleMap.OnMapLoadedCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap map;
    private LatLng myLocation;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapFragment mf = (MapFragment) getFragmentManager().findFragmentById(R.id.the_map);
        mf.getMapAsync(this);


}



    @Override
    public void onMapReady(GoogleMap map) {    // map is loaded but not laid out yet
        this.map = map;
        map.setOnMapLoadedCallback(this);   map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(-18.142, 178.431), 2));
        map.addPolyline(new PolylineOptions().geodesic(true)
                .add(new LatLng(-33.866, 151.195))  // Sydney
                .add(new LatLng(-18.142, 178.431))  // Fiji
                .add(new LatLng(21.291, -157.821))  // Hawaii
                .add(new LatLng(37.423, -122.091))  // Mountain View
        );

        // Other supported types include: MAP_TYPE_NORMAL,
        // MAP_TYPE_TERRAIN, MAP_TYPE_HYBRID and MAP_TYPE_NONE
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }
            // calls onMapLoaded when layout done
            private void setUpMap() {

                map.addMarker(
                        new MarkerOptions()
                                .position(new LatLng(-33.866, 151.195))
                                .snippet("Pizza Hut")
                                .flat(true)
                                .title("Rama 9!"));
                map.addMarker(
                        new MarkerOptions()
                                .position(new LatLng(-18.142, 178.431))
                                .snippet("Pizza Hut")
                                .flat(true)
                                .title("Sukhamwit"));
                map.addMarker(
                        new MarkerOptions()
                                .position(new LatLng(21.291, -157.821))
                                .snippet("Pizza Hut")
                                .flat(true)
                                .title("BangNa"));
                map.addMarker(
                        new MarkerOptions()
                                .position(new LatLng(37.423, -122.091))
                                .snippet("Pizza Hut")
                                .flat(true)
                                .title("Ramkhamhaeng"));
            }

    @Override
    public void onMapLoaded() {
        // code to run when the map has loaded
        readCities();
        map.setOnMarkerClickListener(this);

        // read user's current location, if possible
        myLocation = getMyLocation();
        if (myLocation == null) {
            Toast.makeText(this, "Unable to access your location. Consider enabling Location in your device's Settings.", Toast.LENGTH_LONG).show();
        } else {
            map.addMarker(new MarkerOptions()
                            .position(myLocation)
                            .title("ME!")
            );
        }
    }

    /*
     * Reads a list of cities from a text file and draws a marker for each one on the map.
     */
    private void readCities() {
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.cities));
        while (scan.hasNextLine()) {
            String name = scan.nextLine();
            if (name.isEmpty()) break;
            double lat = Double.parseDouble(scan.nextLine());
            double lng = Double.parseDouble(scan.nextLine());
            // add marker here..
            map.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title(name));
            //Marker mark = map.addMarker(new MarkerOptions());//remove/modify the mark
            //mark.remove();

        }
    }

    /*
     * Returns the user's current location as a LatLng object.
     * Returns null if location could not be found (such as in an AVD emulated virtual device).
     */
    private LatLng getMyLocation() {
        // try to get location three ways: GPS, cell/wifi network, and 'passive' mode
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (loc == null) {
            // fall back to network if GPS is not available
            loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        if (loc == null) {
            // fall back to "passive" location if GPS and network are not available
            loc = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        }

        if (loc == null) {
            return null;   // could not get user's location
        } else {
            double myLat = loc.getLatitude();
            double myLng = loc.getLongitude();
            return new LatLng(myLat, myLng);
        }
    }

    /*
     * Called when user clicks on any of the city map markers.
     * Adds a line from the user's location to that city.
     */
    @Override
    public boolean onMarkerClick(Marker marker) {
        if (myLocation != null) {
            // add poly line here..
            LatLng markerLatLng = marker.getPosition();
            map.addPolygon(new PolygonOptions().add(myLocation)
                            .add(markerLatLng)
            );
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.normal:
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case R.id.hybrid:
                map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case R.id.satellite:
                map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.terrain:
                map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    public void OnClick(View v) {
        if (v.getId() == R.id.login) {
            Intent i = new Intent(MainActivity.this, MainActivity.class);
            startActivity(i);
        }
    }

    public void OnButtonClick(View v) {
    if (v.getId() == R.id.menu) {
        Intent i = new Intent(MainActivity.this, toping.class);
        startActivity(i);
    }
}
    public void onButtonClick(View v) {
        if (v.getId() == R.id.next_2) {
            Intent i = new Intent(MainActivity.this, address.class);
            startActivity(i);
        }

}
    public void onbuttonclick(View v) {
        if (v.getId() == R.id.next_3) {
            Intent i = new Intent(MainActivity.this, delivery.class);
            startActivity(i);
        }

    }

    public void buttonclick(View v) {
        if (v.getId() == R.id.next_4) {
            Intent i = new Intent(MainActivity.this, exit.class);
            startActivity(i);
        }

    }
    public void clickextra(View v) {
        if (v.getId() == R.id.Next_2) {
            Intent i = new Intent(MainActivity.this, delivery.class);
            startActivity(i);
        }

    }
    public void clickspicy(View v) {
        if (v.getId() == R.id.Next_3) {
            Intent i = new Intent(MainActivity.this, delivery.class);
            startActivity(i);
        }

    }
    public void clicksupreme(View v) {
        if (v.getId() == R.id.Next_4) {
            Intent i = new Intent(MainActivity.this, delivery.class);
            startActivity(i);
        }

    }

    public void clickbbq(View v) {
        if (v.getId() == R.id.Next_1) {
            Intent i = new Intent(MainActivity.this, delivery.class);
            startActivity(i);
        }

    }

}
