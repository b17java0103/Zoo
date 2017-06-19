package com.example.gagan.gridview;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.gagan.ExtraClasses.InternetAlert;
import com.google.android.gms.maps.model.LatLng;

public class DirectionActivity extends AppCompatActivity {
    public static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 11;

    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    String lat;
    String provider;
    double latitude, longitude,des_lat,des_lng;
    int visit;
    WebView web;
    String url1 = "";
    SharedPreferences prf;

    boolean isGranted;
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);

            web = (WebView) findViewById(R.id.webview);
            prf = this.getSharedPreferences("anything", MODE_PRIVATE);

            visit = getIntent().getIntExtra("visitor", 0);
            //Toast.makeText(getApplicationContext(), "...." + visit, Toast.LENGTH_SHORT).show();
            if (visit == 1) {
                des_lat = Double.parseDouble(getIntent().getStringExtra("visitor_lat"));
                des_lng = Double.parseDouble(getIntent().getStringExtra("visitor_lng"));
            } else if (visit == 2) {
                des_lat = Double.parseDouble(getIntent().getStringExtra("visitor_latt"));
                des_lng = Double.parseDouble(getIntent().getStringExtra("visitor_lngg"));

            } else {
                des_lat = getIntent().getDoubleExtra("destination_lat", 0.0);
                des_lng = getIntent().getDoubleExtra("destination_lng", 0.0);
            }
            gps = new GPSTracker(DirectionActivity.this, DirectionActivity.this);

            if (gps.canGetLocation()) {


                longitude = gps.getLongitude();
                latitude = gps.getLatitude();
            } else {

                gps.showSettingsAlert();
            }
        /*longitude=;
        latitude=;
        */

            /*double Curlatitude = Double.parseDouble(prf.getString("lat", "0.0"));
            double Curlongitude = Double.parseDouble(prf.getString("lng", "0.0"));
            */
            //Toast.makeText(getApplicationContext(), " " + latitude, Toast.LENGTH_LONG).show();
            //Toast.makeText(getApplicationContext(), " " + longitude, Toast.LENGTH_LONG).show();

            url1 = "http://maps.google.com/maps?saddr=" + latitude  + ","
                    + longitude + "&daddr=" + des_lat + "," + des_lng;

            web.getSettings().setJavaScriptEnabled(true);
            web.loadUrl(url1);

            web.setWebViewClient(new WebViewClient() {

                public void onProgressChanged(WebView view, int progress) {
                    DirectionActivity.this.setTitle("Loading...");
                    DirectionActivity.this.setProgress(progress * 100);
                    if (progress == 100)
                        DirectionActivity.this.setTitle("My title");
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return false;
                }

            });


        }

    }
