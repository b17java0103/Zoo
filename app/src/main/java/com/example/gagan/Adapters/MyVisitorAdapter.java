package com.example.gagan.Adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.gagan.ExtraClasses.VisitorArrayGroup;
import com.example.gagan.gridview.R;

/**
 * Created by GaGan on 5/4/2017.
 */

public class MyVisitorAdapter extends BaseAdapter {
    Context c;
    LayoutInflater inf;
    public MyVisitorAdapter(Context activity) {
        c = activity;
        inf = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return VisitorArrayGroup.img_visitor.length;
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
        TextView txt2;
        ImageView img;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;

        if (convertView == null) {
            vh = new ViewHolder();
            convertView = inf.inflate(R.layout.cust_visitor, null);
            vh.img = (ImageView) convertView.findViewById(R.id.img);
            vh.txt2 = (TextView) convertView.findViewById(R.id.text);

            vh.img.setBackgroundResource(VisitorArrayGroup.img_visitor[position]);
            vh.txt2.setText(VisitorArrayGroup.visitor_title[position]);



    /*    ImageView img = new ImageView(c);
        img.setBackgroundResource(VisitorArrayGroup.img_visitor[position]);

        return img;
    */
        }   else
        {
            vh = (ViewHolder)convertView.getTag();
        }

        return convertView;

    }
}
