package com.example.gagan.gridview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.gagan.Fragments.Adoption;
import com.example.gagan.Fragments.Overview;

public class adopt extends AppCompatActivity /*implements View.OnClickListener*/ {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
   /*     getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Adoption adoption=new Adoption();
android.support.v4.app.FragmentTransaction over=getSupportFragmentManager().beginTransaction();
        over.replace(R.id.frm,adoption);
        over.commit();
    }
*/
    }    @Override
        protected void onPause() {
            super.onPause();
            finish();
        }
    }
