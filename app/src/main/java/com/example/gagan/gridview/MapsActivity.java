package com.example.gagan.gridview;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.gagan.ExtraClasses.InternetAlert;
import com.example.gagan.Model_Classes.Model_Getter_Setter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    //init values
    public static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 11;
    public static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 121;

    boolean isGranted,checkper;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    String provider;
    protected boolean gps_enabled, network_enabled;
    private GoogleMap mMap;
    double latitude1, longitude1;
    LatLng mar1,aaa,gate;
    LatLng current;
    FloatingActionButton fab1;
    ArrayList<Model_Getter_Setter> arr = new ArrayList<Model_Getter_Setter>();
    SharedPreferences prf;
    GPSTracker gps;
    Marker mMarker;
    int check, check2;
    ArrayList<Double> lat = new ArrayList<Double>();
    ArrayList<Double> lng = new ArrayList<Double>();
    ArrayList<String> title = new ArrayList<String>();

    ArrayList<Double> latcust = new ArrayList<Double>();
    ArrayList<Double> lngcust = new ArrayList<Double>();
    ArrayList<String> titlecust = new ArrayList<String>();
    SupportMapFragment mapFragment;
    LocationListener mloLocationListener;



    /*
    MapsActivity(Marker marker)
    {
        mMarker=marker;
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

            mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            arr = (ArrayList<Model_Getter_Setter>) getIntent().getSerializableExtra("latlng");
            check = getIntent().getIntExtra("checkkey", 0);
            lat = (ArrayList<Double>) getIntent().getSerializableExtra("latkey");
            lng = (ArrayList<Double>) getIntent().getSerializableExtra("lngkey1");
            title = (ArrayList<String>) getIntent().getSerializableExtra("titlekey");

            latcust = (ArrayList<Double>) getIntent().getSerializableExtra("latkey22");
            lngcust = (ArrayList<Double>) getIntent().getSerializableExtra("lngkey22");
            titlecust = (ArrayList<String>) getIntent().getSerializableExtra("titlekey22");
            check2 = getIntent().getIntExtra("checkkey2", 0);


            prf = this.getSharedPreferences("anything", MODE_PRIVATE);
            gps = new GPSTracker(MapsActivity.this, MapsActivity.this);


            if (gps.canGetLocation()) {


                double longitude = gps.getLongitude();
                double latitude = gps.getLatitude();
                aaa = new LatLng(latitude, longitude);
                Toast.makeText(getApplicationContext(), "Longitude:" + Double.toString(longitude) + "\nLatitude:" + Double.toString(latitude), Toast.LENGTH_SHORT).show();
            } else {

                gps.showSettingsAlert();
            }


            checkper = checkPermission();
        }


    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            isGranted = false;
            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }else  if (ContextCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            isGranted = false;
            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
        }

        else {
            isGranted = true;
           mapFragment.getMapAsync(MapsActivity.this);
           // locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,mloLocationListener);

        }
        return isGranted;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //Marker for current location
        mMap.addMarker(new MarkerOptions().position(aaa).title("You are Here"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(aaa,20));

        //Marker for Gate
         gate = new LatLng(30.605751, 76.799134);
        mMap.addMarker(new MarkerOptions().position(gate).title("Entery Gate"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gate,20));


        // Add a marker in Sydney and move the camera
          if (check == 1) {
              for (int i = 0; i < lat.size(); i++) {

                  LatLng zoo = new LatLng(lat.get(i), lng.get(i));
                  mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

                  mMap.addMarker(new MarkerOptions().position(zoo).title(title.get(i)).icon(BitmapDescriptorFactory.fromResource(R.drawable.foodd)));
                  mMap.moveCamera(CameraUpdateFactory.newLatLng(zoo));

                  mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));

              }
          } else if (check2 == 2) {

              for (int i = 0; i < lat.size(); i++) {
                  LatLng zoo = new LatLng(lat.get(i), lng.get(i));
                  mMap.moveCamera(CameraUpdateFactory.newLatLng(zoo));
                  mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));

                  mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

                  if (title.get(i).equalsIgnoreCase("Reptile House")) {
                      mMap.addMarker(new MarkerOptions().position(zoo).title(title.get(i)).icon(BitmapDescriptorFactory.fromResource(R.drawable.crocodileee)));
                  } else if (title.get(i).equalsIgnoreCase("Lion Safari")) {
                      mMap.addMarker(new MarkerOptions().position(zoo).title(title.get(i)).icon(BitmapDescriptorFactory.fromResource(R.drawable.dd)));

                  } else if (title.get(i).equalsIgnoreCase("Black Bear")) {
                      mMap.addMarker(new MarkerOptions().position(zoo).title(title.get(i)).icon(BitmapDescriptorFactory.fromResource(R.drawable.bear)));

                  } else if (title.get(i).equalsIgnoreCase("Elephant")) {
                      mMap.addMarker(new MarkerOptions().position(zoo).title(title.get(i)).icon(BitmapDescriptorFactory.fromResource(R.drawable.hippo)));

                  } else {
                  }
              }

          } else {
              for (int i = 0; i < arr.size(); i++) {
                  String[] latlong = (arr.get(i).getLngLat()).split(",");
                  double latitude = Double.parseDouble(latlong[0]);
                  double longitude = Double.parseDouble(latlong[1]);
                  LatLng zoo = new LatLng(latitude, longitude);
                  mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                  if (arr.get(i).getCategory().equalsIgnoreCase("rapitile")) {
                      mMap.addMarker(new MarkerOptions().position(zoo).title(arr.get(i).getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.crocodileee)));
                  } else if (arr.get(i).getCategory().equalsIgnoreCase("Carnivorous")) {
                      mMap.addMarker(new MarkerOptions().position(zoo).title(arr.get(i).getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.dd)));
                  } else if (arr.get(i).getCategory().equalsIgnoreCase("Harbivorous")) {
                      mMap.addMarker(new MarkerOptions().position(zoo).title(arr.get(i).getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.hippo)));
                  } else if (arr.get(i).getCategory().equalsIgnoreCase("Mammal")) {
                      mMap.addMarker(new MarkerOptions().position(zoo).title(arr.get(i).getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.cow)));
                  } else if (arr.get(i).getCategory().equalsIgnoreCase("Bird")) {
                      mMap.addMarker(new MarkerOptions().position(zoo).title(arr.get(i).getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.birds)));
                  } else if (arr.get(i).getCategory().equalsIgnoreCase("Omnivorous")) {
                      mMap.addMarker(new MarkerOptions().position(zoo).title(arr.get(i).getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.bear)));
                  } else {

                  }

                  mMap.moveCamera(CameraUpdateFactory.newLatLng(zoo));

                  //  mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                  mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
              }
          }

       /* Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap bmp = Bitmap.createBitmap(80, 80, conf);
        Canvas canvas1 = new Canvas(bmp);*/
          mMap.setOnMarkerClickListener(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 11 || requestCode==121) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                isGranted = true;
                checkPermission();
            } else {
                isGranted = false;
                Log.e("log", "Please Accept App Permissions.");
            }
        }
        }

    @Override
    public boolean onMarkerClick(Marker marker) {
        mar1 = marker.getPosition();

        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);

// 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("Do you want to Navigate")
                .setTitle("GPS");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button

                Intent in = new Intent(MapsActivity.this, DirectionActivity.class);
               in.putExtra("destination_lat", mar1.latitude);
                in.putExtra("destination_lng", mar1.longitude);
                /*in.putExtra("current_lat1", latitude1);
                in.putExtra("current_lng1", longitude1);*/
                startActivity(in);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        return false;
    }

   /* @Override
    public void onLocationChanged(Location location) {
        //  txtLat = (TextView) findViewById(R.id.textview1);
        // txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
        latitude1 = location.getLatitude();
        longitude1 = location.getLongitude();
        current = new LatLng(latitude1, longitude1);

        SharedPreferences.Editor edtor = prf.edit();
        edtor.putString("lat", "" + latitude1);
        edtor.putString("lng", "" + longitude1);

        edtor.commit();
        Toast.makeText(getApplication(),"....."+latitude1, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude", "disable");

    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude", "enable");

    }


    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude", "status");

    }

*/
}
