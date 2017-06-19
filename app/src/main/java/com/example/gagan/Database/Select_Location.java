package com.example.gagan.Database;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.gagan.Model_Classes.Model_Getter_Setter;
import com.example.gagan.gridview.MapsActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by GaGan on 4/18/2017.
 */

public class Select_Location extends AsyncTask<String,String,String> {


        ProgressDialog pDialog;
        Context c;
        post obj;
        StringBuilder response;
        ArrayList<Model_Getter_Setter> arrlst = new ArrayList<Model_Getter_Setter>();
        Model_Getter_Setter model;


public Select_Location(Context c)
        {
        this.c = c;

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
        String url = "http://welcometozoo.16mb.com/VisitToZoo.php?act=Selection_Location";
        obj = new post(url);
        response = obj.Connect();
        try {
            JSONObject jsonObj = new JSONObject(response.toString());

            JSONArray jsonArray = jsonObj.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj2 = jsonArray.getJSONObject(i);
                String LngLat = obj2.getString("LngLat");
                String Name = obj2.getString("Name");
                String Cat=obj2.getString("Cat");
                model = new Model_Getter_Setter(LngLat,Name,Cat);
                arrlst.add(model);


                //  mod= new Model_Select(BTES_ID,NAME,BRANCH,FA_NAME,DOB,GENDER,ADDRESS,PINCODE,CONTACT,EMAIL,ACADEMIC,DEGREE_REC,BACKLOGS,GAP,REASON,Interested);

                //  arrayList.add(mod);

            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    return null;
        }

protected void onPostExecute(String aExec) {
        super.onPostExecute(aExec);
        pDialog.dismiss();

    Intent in = new Intent(c, MapsActivity.class);
    in.putExtra("latlng",arrlst);
    c.startActivity(in);
        }

        }