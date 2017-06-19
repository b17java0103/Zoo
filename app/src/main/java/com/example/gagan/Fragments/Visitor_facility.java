package com.example.gagan.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.gagan.Adapters.MajorAttrac;
import com.example.gagan.Adapters.MyVisitorAdapter;
import com.example.gagan.ExtraClasses.MajorAttracDia;
import com.example.gagan.gridview.MapsActivity;
import com.example.gagan.gridview.R;
import com.example.gagan.gridview.ViewVisitorDialog;

import java.util.ArrayList;

import static com.example.gagan.gridview.R.id.gridView;

public class Visitor_facility extends Fragment {
    FloatingActionButton floatingActionButton;
    Double latitudevisitor, longitudevisitor;
GridView grd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_visitor_facility, container, false);
  /*floatingActionButton=(FloatingActionButton) rootview.findViewById(R.id.fabb);
        floatingActionButton.setOnClickListener(this);
*/ grd = (GridView) rootview.findViewById(R.id.grd_major);
        Window window = getActivity().getWindow();
/*

        float scalefactor = getResources().getDisplayMetrics().density * 100;
        int number = window.getWindowManager().getDefaultDisplay().getWidth();
        int columns = (int) ((float) number / (float) scalefactor);
        grd.setNumColumns(columns);
*/

        grd.setAdapter(new MajorAttrac(getActivity()));

        grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new MajorAttracDia(getActivity(),position);
            }
        });
        return rootview;

    }
}