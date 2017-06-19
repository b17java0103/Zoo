package com.example.gagan.gridview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gagan.Database.Select_Name;
import com.example.gagan.Database.select_info;
import com.example.gagan.Model_Classes.Model_Getter_Setter;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
ImageView img;
    TextView his;

    TextView textDiet;
    TextView textHab;
    TextView textName;
    TextView textSci;

    ArrayList<Model_Getter_Setter> arrlst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(this);
        textHab=(TextView) findViewById(R.id.textHab) ;
        textDiet=(TextView) findViewById(R.id.textDiet) ;
        textSci=(TextView) findViewById(R.id.textSci) ;
        textName=(TextView) findViewById(R.id.textName) ;


        his=(TextView) findViewById(R.id.text) ;
        img=(ImageView) findViewById(R.id.img);
        String name = getIntent().getStringExtra("name");

        new select_info(this,textName,textSci,textDiet,textHab,his,img,toolbar).execute(name);

    }

    @Override
    public void onClick(View v) {
        Intent ine=new Intent(this,CatList.class);
        ine.putExtra("key2",10);
    startActivity(ine);
    }

    @Override
    protected void onPause() {
        super.onPause();
    finish();
    }
}
