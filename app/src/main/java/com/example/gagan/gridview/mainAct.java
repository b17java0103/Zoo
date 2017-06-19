package com.example.gagan.gridview;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gagan.Database.Select_Location;
import com.example.gagan.ExtraClasses.InternetAlert;
import com.example.gagan.Fragments.Adoption;
import com.example.gagan.Fragments.CatAnimal;
import com.example.gagan.Fragments.Feedback;
import com.example.gagan.Fragments.Home;
import com.example.gagan.Fragments.Overview;
import com.example.gagan.Fragments.Time_Tariff;
import com.example.gagan.Fragments.Visitor;
import com.example.gagan.Fragments.Visitor_facility;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

import static com.example.gagan.gridview.R.drawable.e;

public class mainAct extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Animation.AnimationListener, View.OnClickListener {

    public static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 11;
    public static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 121;


    TextView txtvwdata;
    TextView open;
    Animation animationblink;
    String s1, s2, s3,s4;
    boolean doubleBackToExitPressedOnce = false;
    String w;
    Toolbar toolbar;
    ArrayList<Double> latcust;
    ArrayList<Double> lngcust;
    ArrayList<String> titlecust;
    boolean isGranted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Welcome to The Zoo");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.mipmap.hom);
        checkPermission();
        open = (TextView) findViewById(R.id.open);
        txtvwdata = (TextView) findViewById(R.id.txtvwdata);
        animationblink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink_animation);
        animationblink.setAnimationListener(this);
        txtvwdata.setVisibility(View.VISIBLE);
        txtvwdata.startAnimation(animationblink);
        open.setVisibility(View.VISIBLE);
        open.startAnimation(animationblink);

        String op="Open Today";
        String close="Close Today";
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");

        String time1 = "17:00:00";
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String timeStamp =format.format(Calendar.getInstance().getTime());
        Date date1 = null;



        try {
            date1 = format.parse(time1);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        Date date2 = null;
        try {
            date2 = format.parse(timeStamp);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        Date d=new Date();
        String dayOfTheWeek = sdf.format(d);
        long diff = date1.getTime() - date2.getTime();

        // System.out.println("Time :: "+difference/60000);
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        if(diffSeconds<0||diffMinutes<0||diffHours<0||diffHours>8)
        {
            w="Visit Timing: 9am to 5pm";
            open.setText(w);
        }

        else {

            if(dayOfTheWeek.equals("Monday"))
            {

                txtvwdata.setText(close);
                w="Visit Timing :9am to 5pm";

            }
            else{
                txtvwdata.setText(op);
                s1 = diffHours + "hrs";
                s2 = diffMinutes + "mins";

                s3="to Close";
                open.setText(s1+" " + s2 + " " + s3);


            }


        }






   /*     FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               *//* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
*//*
        new Select_Location(mainAct.this).execute();
            }
        });
*/
        Button btnmap=(Button) findViewById(R.id.btnmap);
        btnmap.setOnClickListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }  if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }  this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Homepage) {
               toolbar.setTitle("Welcome To The Zoo");
            Home vvst=new Home();
            android.support.v4.app.FragmentTransaction fragt=getSupportFragmentManager().beginTransaction();
            fragt.replace(R.id.frame,vvst);
            fragt.commit();


           /* Home home=new Home();
            android.support.v4.app.FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame,home);
            transaction.commit();
           */ // Handle the camera action

        }

        else if(id==R.id.Overviews)
        {
            toolbar.setTitle("Overview");
            Overview overview=new Overview();
            android.support.v4.app.FragmentTransaction over=getSupportFragmentManager().beginTransaction();
            over.replace(R.id.frame,overview);
            over.commit();

        }

        else if (id == R.id.Animals) {
            toolbar.setTitle("Category of Animals");
            CatAnimal second=new CatAnimal();
            android.support.v4.app.FragmentTransaction FragmentTransaction1=getSupportFragmentManager().beginTransaction();
            FragmentTransaction1.replace(R.id.frame,second);
            FragmentTransaction1.commit();

        } else if (id == R.id.Gallary) {
            Intent e=new Intent(this, Gallary.class);
            startActivity(e);

        } else if (id == R.id.adopt) {
            toolbar.setTitle("Adoption");
            Adoption adoption=new Adoption();
            android.support.v4.app.FragmentTransaction adoptions=getSupportFragmentManager().beginTransaction();
            adoptions.replace(R.id.frame,adoption);
            adoptions.commit();

        } else if (id == R.id.attraction) {
            toolbar.setTitle("Major Attractions");
            Visitor_facility visit=new Visitor_facility();
            android.support.v4.app.FragmentTransaction visitf=getSupportFragmentManager().beginTransaction();
            visitf.replace(R.id.frame,visit);
            visitf.commit();

        }
        else if (id == R.id.timetariff) {
            toolbar.setTitle("Timings & Tariffs");
            Time_Tariff time_tariff=new Time_Tariff();
            android.support.v4.app.FragmentTransaction tim=getSupportFragmentManager().beginTransaction();
            tim.replace(R.id.frame,time_tariff);
            tim.commit();
        }
        else if(id== R.id.visitorfac)
        {
            toolbar.setTitle("Visitor Facilities");
            Visitor vst=new Visitor();
            android.support.v4.app.FragmentTransaction fragvst=getSupportFragmentManager().beginTransaction();
            fragvst.replace(R.id.frame,vst);
            fragvst.commit();

        }

        else if (id == R.id.food) {
            toolbar.setTitle("Food Points");
            ArrayList<Double> lat = new ArrayList<Double>();
            ArrayList<Double> lng = new ArrayList<Double>();
            ArrayList<String> title = new ArrayList<String>();

            //  title.add("Your Location");
            lat.add(30.60184805);   lng.add(76.79112069);   title.add("Food Point");
            lat.add(30.605124);     lng.add(76.799555);     title.add("Food Point");
            Intent inn=new Intent(getApplicationContext(),MapsActivity.class);
            inn.putExtra("latkey",lat);
            inn.putExtra("lngkey1",lng);
            inn.putExtra("titlekey",title);
            inn.putExtra("checkkey",1);
            startActivity(inn);

        }
        else if (id == R.id.custom)
        {

            latcust = new ArrayList<Double>();
            lngcust = new ArrayList<Double>();
            titlecust = new ArrayList<String>();

            //  title.add("Your Location");
            latcust.add(30.607306);   lngcust.add(76.800001);   titlecust.add("Elephant");
            latcust.add(30.605124);     lngcust.add(76.799555);     titlecust.add("Lion Safari");
            latcust.add(30.603959);     lngcust.add(76.796218);     titlecust.add("Black Bear");
            latcust.add(30.60464904);     lngcust.add(76.79189818);     titlecust.add("Reptile House ");
            AlertDialog.Builder builder = new AlertDialog.Builder(mainAct.this);

// 2. Chain together various setter methods to set the dialog characteristics
            builder.setMessage("You can save your time by visiting our Major Attractions ")
                    .setTitle("Time Saving Tour");

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    if (!InternetAlert.isConnected(mainAct.this)) {
                        InternetAlert.showSettingsAlert();
                        InternetAlert.showGPSAlert();

                    }else {

                        // User clicked OK button
                        Intent inn = new Intent(getApplicationContext(), MapsActivity.class);
                        inn.putExtra("latkey", latcust);
                        inn.putExtra("lngkey1", lngcust);
                        inn.putExtra("titlekey", titlecust);
                        inn.putExtra("checkkey2", 2);
                        startActivity(inn);
                    }
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




        }/*else if (id == R.id.shareit) {
            ApplicationInfo api=getApplicationContext().getApplicationInfo();
            String apkPth=api.sourceDir;
            Intent intap=new Intent(Intent.ACTION_SEND);
            intap.setType("application/vnd.android.package-archive");
            intap.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkPth)));
            startActivity(Intent.createChooser(intap,"Share app using"));
            return true;
        }*/
        else if (id == R.id.feedback) {
/*
            toolbar.setTitle("Share Your View");
            Feedback fd=new Feedback();
            android.support.v4.app.FragmentTransaction fragvst=getSupportFragmentManager().beginTransaction();
            fragvst.replace(R.id.frame,fd);
            fragvst.commit();
*/
            String[] TO = {"Gagandeep485@gmail.com"};
            String[] CC = {""};

            Intent emailIntent = new Intent(Intent.ACTION_SEND);

            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your Feedback");
          //  emailIntent.putExtra(Intent.EXTRA_TEXT, "Message : "+about.getText().toString());

            try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
         //   this.finish();
            //Log.i("Finished sending email...", "");
            } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
            }
        }

        else if(id==R.id.fulltour)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(mainAct.this);

// 2. Chain together various setter methods to set the dialog characteristics
            builder.setMessage("Estimate Time for Full Tour is 2hrs 30mins ")
                    .setTitle("Full Tour");

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                    if (!InternetAlert.isConnected(mainAct.this)) {
                        InternetAlert.showSettingsAlert();
                        InternetAlert.showGPSAlert();

                    }else {

                        new Select_Location(mainAct.this).execute();
                    }
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
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if(animation==animationblink) {
        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mainAct.this);

// 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("Estimate Time for Full Tour is 2 hrs 30 mins ")
                .setTitle("Full Tour");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                if (!InternetAlert.isConnected(mainAct.this)) {
                    InternetAlert.showSettingsAlert();
                    InternetAlert.showGPSAlert();
                }else {

                    new Select_Location(mainAct.this).execute();
                }
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


    }


    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(mainAct.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            isGranted = false;
            ActivityCompat.requestPermissions(mainAct.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }else  if (ContextCompat.checkSelfPermission(mainAct.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            isGranted = false;
            ActivityCompat.requestPermissions(mainAct.this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
        }

        else {
            isGranted = true;
            // mapFragment.getMapAsync(MapsActivity.this);
            // locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,mloLocationListener);

        }
        return isGranted;
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

}
