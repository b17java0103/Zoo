package com.example.gagan.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.gagan.Adapters.MyAdapter;
import com.example.gagan.gridview.CatList;
import com.example.gagan.gridview.R;


public class CatAnimal extends Fragment {
   public static String arr[] = {"Bird", "Carnivorous", "Herbivorous","Mammal","Omnivorous","Reptile"};
    public static String arr1[] = {"Birds", "Carnivorous", "Herbivorous","Mammals","Omnivorous","Reptiles"};

    public static int img[] = {R.drawable.bird, R.drawable.carnivorous, R.drawable.harbivorous,R.drawable.mam,R.drawable.omnivors,R.drawable.rept};

    GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_blank, container, false);

        gridView = (GridView) rootview.findViewById(R.id.gridview);
        gridView.setAdapter(new MyAdapter(getActivity(), arr1,img));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(getActivity(), "" + position, Toast.LENGTH_LONG).show();

                Intent i = new Intent(getActivity(), CatList.class);
                i.putExtra("key4", arr[position]);
                i.putExtra("key2", position);
                startActivity(i);

            }
        });
        return rootview;
    }
    }

