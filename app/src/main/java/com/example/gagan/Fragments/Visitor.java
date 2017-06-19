package com.example.gagan.Fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gagan.Adapters.MyVisitorAdapter;
import com.example.gagan.gridview.R;
import com.example.gagan.gridview.ViewVisitorDialog;

import java.io.ByteArrayOutputStream;

public class Visitor extends Fragment {


    /*
ImageView imageView3,imageView4,imageView7,imageView8,imageView9,imageView10;
    TextView textView6,textView7,textView8,textView9,textview10,textView15,textview16,textView17,textview18,textView19,textview20,textView21,textview22;
*/
GridView grd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_visitor, container, false);
        grd = (GridView) rootview.findViewById(R.id.grd_visitor);
        grd.setAdapter(new MyVisitorAdapter(getActivity()));

        grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new ViewVisitorDialog(getActivity(),position);
            }
        });
/*

        imageView4=(ImageView) rootview.findViewById(R.id.imageView4);

        imageView7=(ImageView) rootview.findViewById(R.id.imageview7);

        imageView8=(ImageView) rootview.findViewById(R.id.imageview8);

        imageView9=(ImageView) rootview.findViewById(R.id.imageview9);

        imageView10=(ImageView) rootview.findViewById(R.id.imageview10);

        imageView3=(ImageView) rootview.findViewById(R.id.imageView3);


        textView6=(TextView) rootview.findViewById(R.id.textView6);
        textView7=(TextView) rootview.findViewById(R.id.textView7);
        textView8=(TextView) rootview.findViewById(R.id.textView8);
        textView9=(TextView) rootview.findViewById(R.id.textView9);
        textview10=(TextView) rootview.findViewById(R.id.textview10);
        textView15=(TextView) rootview.findViewById(R.id.textView15);
        textview16=(TextView) rootview.findViewById(R.id.textview16);
        textView17=(TextView) rootview.findViewById(R.id.textView17);
        textview18=(TextView) rootview.findViewById(R.id.textview18);
        textView19=(TextView) rootview.findViewById(R.id.textView19);
        textview20=(TextView) rootview.findViewById(R.id.textview20);
        textView21=(TextView) rootview.findViewById(R.id.textView21);
        textview22=(TextView) rootview.findViewById(R.id.textview22);
*/



        return rootview;
    }

}
