package com.example.gagan.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gagan.gridview.R;
import com.example.gagan.gridview.mainAct;

public class Home extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
View rootview= inflater.inflate(R.layout.fragment_home2, container, false);
        Intent i=new Intent(getActivity(),mainAct.class);
        startActivity(i);



        return rootview;


    }

}
