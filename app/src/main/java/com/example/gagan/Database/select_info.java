package com.example.gagan.Database;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gagan.Adapters.MyCatAdapter;
import com.example.gagan.Model_Classes.Model_Getter_Setter;
import com.example.gagan.gridview.DetailActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class select_info extends AsyncTask<String, String, String> {


    ProgressDialog pDialog;
    Context c;
    TextView text;
    ImageView img;
    post obj;
    StringBuilder response;
    ArrayList<Model_Getter_Setter> arrlst = new ArrayList<Model_Getter_Setter>();
    Model_Getter_Setter model;

    TextView textName;
    TextView textSci;
    TextView textDiet;
    TextView textHab;
    TextView his;
    Toolbar tool;
    public select_info(Context c, TextView textName, TextView textSci, TextView textDiet, TextView textHab, TextView his, ImageView img, Toolbar tool) {
        this.c = c;
        this.textName = textName;
        this.textSci = textSci;
        this.textDiet = textDiet;
        this.textHab = textHab;
        this.his = his;
        this.img = img;
        this.tool = tool;

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


        String url = "http://welcometozoo.16mb.com/VisitToZoo.php?act=Selection_Name&Request_Name=" + arg0[0];
        obj = new post(url);
        response = obj.Connect();
        try {
            JSONObject jsonObj = new JSONObject(response.toString());

            JSONArray jsonArray = jsonObj.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj2 = jsonArray.getJSONObject(i);
                String History = obj2.getString("History");
                String Habitate = obj2.getString("Habitate");
                String Diet = obj2.getString("Diet");
                String Scientfic = obj2.getString("Scientfic");
                String Cat = obj2.getString("Cat");
                String Name = obj2.getString("Name");
                String Image = obj2.getString("Image");
                model = new Model_Getter_Setter(History, Habitate, Diet, Scientfic, Cat, Name, Image);

                arrlst.add(model);


            }


        } catch (JSONException e) {
            e.printStackTrace();

        }
        return null;
    }

    protected void onPostExecute(String aExec) {
        super.onPostExecute(aExec);
        pDialog.dismiss();
        tool.setTitle(arrlst.get(0).getName());
        Picasso.with(c).load(arrlst.get(0).getImage()).into(img);
        textName.setText(arrlst.get(0).getName());

        textSci.setText(arrlst.get(0).getScientific());
        textDiet.setText(arrlst.get(0).getDiet());
        textHab.setText(arrlst.get(0).getHabitate());
        his.setText(" History : " + arrlst.get(0).getHistory());


    }
}