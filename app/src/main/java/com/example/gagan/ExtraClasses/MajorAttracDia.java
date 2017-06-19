package com.example.gagan.ExtraClasses;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.gagan.gridview.DirectionActivity;
import com.example.gagan.gridview.R;

/**
 * Created by GaGan on 5/7/2017.
 */

public class MajorAttracDia {

    String latt,lngg;


    public  MajorAttracDia(final Context activity, int pos){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_dialog);



        TextView text = (TextView) dialog.findViewById(R.id.txt_tit);
        TextView text1 = (TextView) dialog.findViewById(R.id.txt_visit);

        text.setText(VisitorArrayGroup.mjorAtt_title[pos]);
        text1.setText(VisitorArrayGroup.majorAtt_disc[pos]);
        latt= VisitorArrayGroup.visitor_latt[pos];
        lngg = VisitorArrayGroup.visitor_lngg[pos];

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
                in.putExtra("visitor",2);
                in.putExtra("visitor_latt",latt);
                in.putExtra("visitor_lngg",lngg);
                activity.startActivity(in);





            }
        });

        dialog.show();

    }

}
