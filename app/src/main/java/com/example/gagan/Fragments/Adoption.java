package com.example.gagan.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gagan.gridview.R;
import com.example.gagan.gridview.adopt;

public class Adoption extends Fragment implements View.OnClickListener {
    Button how;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

      View rootview=  inflater.inflate(R.layout.fragment_adoption, container, false);
        how=(Button) rootview.findViewById(R.id.how);
        how.setOnClickListener(this);
        return rootview;

    }

    @Override
    public void onClick(View v) {
        Intent it;
        it = new Intent(getActivity(),adopt.class);
        startActivity(it);
    }
}
