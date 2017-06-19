package com.example.gagan.gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ViewFlipper;

import com.example.gagan.Fragments.CatAnimal;

public class Second extends AppCompatActivity
{
    ViewFlipper vf;
    int pos;
    int im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        vf= (ViewFlipper) findViewById(R.id.vf);
        pos = getIntent().getIntExtra("key4", 0);
        if (pos == 0) {
            vf.setBackgroundResource(CatAnimal.img[pos]);

        }
    }
}