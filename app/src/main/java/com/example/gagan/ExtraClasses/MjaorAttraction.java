package com.example.gagan.ExtraClasses;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.gagan.gridview.R;

/**
 * Created by GaGan on 5/7/2017.
 */

public class MjaorAttraction {


    public  MjaorAttraction(Context activity, int pos){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_dialog);



        TextView text = (TextView) dialog.findViewById(R.id.txt_tit);
        TextView text1 = (TextView) dialog.findViewById(R.id.txt_visit);

        text.setText(VisitorArrayGroup.mjorAtt_title[pos]);
        text1.setText(VisitorArrayGroup.majorAtt_disc[pos]);

        Button dialogButton = (Button) dialog.findViewById(R.id.btn);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

}
