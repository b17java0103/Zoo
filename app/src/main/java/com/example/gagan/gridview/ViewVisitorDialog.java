package com.example.gagan.gridview;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gagan.ExtraClasses.VisitorArrayGroup;

/**
 * Created by GaGan on 5/4/2017.
 */

public class ViewVisitorDialog {



String lat,lng;
    public  ViewVisitorDialog(final Context activity, int pos){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_dialog);



        TextView text = (TextView) dialog.findViewById(R.id.txt_tit);
        TextView text1 = (TextView) dialog.findViewById(R.id.txt_visit);

        text.setText(VisitorArrayGroup.visitor_title[pos]);
        text1.setText(VisitorArrayGroup.visitor_disc[pos]);
         lat= VisitorArrayGroup.visitor_lat[pos];
         lng = VisitorArrayGroup.visitor_lng[pos];

        Button dialogButton = (Button) dialog.findViewById(R.id.btn);
        Button dialogButton1 = (Button) dialog.findViewById(R.id.btn_nv);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialogButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(activity,DirectionActivity.class);
                in.putExtra("visitor",1);
                in.putExtra("visitor_lat",lat);
                in.putExtra("visitor_lng",lng);
                activity.startActivity(in);

            }
        });

        dialog.show();

    }
}
