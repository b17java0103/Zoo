package com.example.gagan.Database;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.gagan.Adapters.MyCatAdapter;
import com.example.gagan.Model_Classes.Model_Getter_Setter;
import com.example.gagan.gridview.DetailActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gagan.Adapters.MyCatAdapter;
import com.example.gagan.Model_Classes.Model_Getter_Setter;
import com.example.gagan.gridview.DetailActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by GaGan on 3/31/2017.
 */


public class Select_Name extends AsyncTask<String,String,String> {


    ProgressDialog pDialog;
    Context c;
    post obj;
    StringBuilder response;
    ArrayList<Model_Getter_Setter> arrlst = new ArrayList<Model_Getter_Setter>();
    Model_Getter_Setter model;

    GridView lst;
    public Select_Name(Context c, GridView lst)
    {
        this.c = c;
        this.lst = lst;



    }

    protected void onPreExecute() {
        super.onPreExecute();
        // Showing progress dialog
        pDialog = new ProgressDialog(c);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

    }

    @Override
    protected String doInBackground(String... arg0) {


        String url = "http://welcometozoo.16mb.com/VisitToZoo.php?act=Selection&Request_Cat=" + arg0[0];
        obj = new post(url);
        response = obj.Connect();
        try {
            JSONObject jsonObj = new JSONObject(response.toString());

            JSONArray jsonArray = jsonObj.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj2 = jsonArray.getJSONObject(i);
                String TechName = obj2.getString("TechName");
                String Name = obj2.getString("Name");
                     String LngLat=obj2.getString("LngLat");
               String Image=obj2.getString("Image");
               // String Cat=obj2.getString("Cat");
                model = new Model_Getter_Setter(Name, TechName,LngLat, Image);

                arrlst.add(model);


                //  mod= new Model_Select(BTES_ID,NAME,BRANCH,FA_NAME,DOB,GENDER,ADDRESS,PINCODE,CONTACT,EMAIL,ACADEMIC,DEGREE_REC,BACKLOGS,GAP,REASON,Interested);

                //  arrayList.add(mod);

            }


        } catch (JSONException e) {
            e.printStackTrace();

        }
        return null;
    }

    protected void onPostExecute(String aExec) {
        super.onPostExecute(aExec);
        pDialog.dismiss();

        lst.setAdapter(new MyCatAdapter(c, arrlst));

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(c, DetailActivity.class);

                in.putExtra("name", arrlst.get(position).getTechName());
                c.startActivity(in);
            }
        });

    }

}