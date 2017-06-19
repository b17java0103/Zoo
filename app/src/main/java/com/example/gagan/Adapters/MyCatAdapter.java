        package com.example.gagan.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.gagan.Model_Classes.Model_Getter_Setter;
import com.example.gagan.gridview.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

        /**
 * Created by GaGan on 3/30/2017.
 */

public class MyCatAdapter extends BaseAdapter {
   // ImageView img;
    LayoutInflater inf;

    Context c;
    ArrayList<Model_Getter_Setter> arrlst;

    public MyCatAdapter(Context c,ArrayList<Model_Getter_Setter> arrlst) {
        this.c = c;
        this.arrlst = arrlst;
                inf = LayoutInflater.from(c);

    }



    @Override
    public int getCount() {
        return arrlst.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder{
        TextView txt1;
        ImageView img;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

/*
        TextView txt = new TextView(c);
        txt.setText(arrlst.get(position).getName());
txt.setTextColor(Color.WHITE);

        return txt;
    }
*/
        ViewHolder vh;


        if(convertView == null)
        {
            vh = new ViewHolder();
           // Log.i(TAG, "getView: "+img);

            convertView =inf.inflate(R.layout.listcust, null);
            vh.img = (ImageView) convertView.findViewById(R.id.image);
            vh.txt1 = (TextView) convertView.findViewById(R.id.textName);

             vh.txt1.setText(arrlst.get(position).getName());
            Picasso.with(c).load(arrlst.get(position).getImage()).resize(100,100).into(vh.img);

        }
        else
        {
            vh = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }


    }
