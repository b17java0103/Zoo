package com.example.gagan.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.gagan.gridview.R;

/**
 * Created by GaGan on 3/21/2017.
 */

public class MyAdapter extends BaseAdapter {
    Context c;
    String[] arr;
    int[] img;
    LayoutInflater inf;

    public MyAdapter(Context c, String[] arr, int[] img) {

        this.c = c;
        this.arr = arr;

        this.img = img;
        inf = LayoutInflater.from(c);

    }

    @Override
    public int getCount() {
        return arr.length;
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
        ViewHolder vh;


        if(convertView == null)
        {
            vh = new ViewHolder();
            convertView =inf.inflate(R.layout.cust_layout, null);
            vh.img = (ImageView) convertView.findViewById(R.id.imageView4);
         vh.txt1 = (TextView) convertView.findViewById(R.id.android_gridview_text);
           // vh.txt2 = (TextView) convertView.findViewById(R.id.textView2);

            vh.img.setBackgroundResource(img[position]);
            vh.txt1.setText(arr[position]);

            //     vh.txt1.setText(arr[position]);
         //   vh.txt2.setText(arr1[position]);

        }
        else
        {
            vh = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }
}
