package com.example.gagan.gridview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

import com.example.gagan.Adapters.MyAdapter;
import com.example.gagan.Database.Select_Name;
import com.example.gagan.Database.select_info;
import com.example.gagan.ExtraClasses.InternetAlert;
import com.example.gagan.Fragments.Adoption;
import com.example.gagan.Fragments.CatAnimal;

public class CatList extends AppCompatActivity /*implements View.OnClickListener*/ {
GridView list_item;
    SharedPreferences shd ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(this);
*/
        shd = this.getSharedPreferences("com.example.gagan.gridview",MODE_PRIVATE);
       list_item=(GridView) findViewById(R.id.list_item) ;
        int pos =  getIntent().getIntExtra("key2",0);
        String Cat = getIntent().getStringExtra("key4");
        if (!InternetAlert.isConnected(CatList.this))

            InternetAlert.showSettingsAlert();
        else {
            if (pos <= 6) {
                SharedPreferences.Editor edt = shd.edit();
                edt.putString("Cat", Cat);
                edt.commit();
                new Select_Name(this, list_item).execute(Cat);

            } else {

                new Select_Name(this, list_item).execute(shd.getString("Cat", null));
            }
        }
    }
/*

    @Override
    public void onClick(View v) {
        CatAnimal adoption=new CatAnimal();
        android.support.v4.app.FragmentTransaction over=getSupportFragmentManager().beginTransaction();
        over.replace(R.id.frm2,adoption);
        over.commit();*/
/*
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);*//*


    }
*/

    @Override
    protected void onPause() {
        super.onPause();
    finish();
    }
}
