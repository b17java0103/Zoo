package com.example.gagan.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gagan.gridview.R;

public class Feedback extends Fragment {
    EditText description,about;
    Button submit;
    String s1,s2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootvew= inflater.inflate(R.layout.activity_email, container, false);

        about=(EditText) rootvew.findViewById(R.id.about);
        submit=(Button) rootvew.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (about.getText().toString().length() == 0) {
                    about.setError("Please enter subject");
                    about.requestFocus();
                }

                 else {
                    Log.i("Send email", "");
                        String[] TO = {"Gagandeep485@gmail.com"};
                        String[] CC = {""};

                        Intent emailIntent = new Intent(Intent.ACTION_SEND);

                        emailIntent.setData(Uri.parse("mailto:"));
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your Feedback");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Message : "+about.getText().toString());

                        try {
                            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                            getActivity().finish();
                            //Log.i("Finished sending email...", "");
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(getActivity(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                        }
                }
            }
        });

        return  rootvew;
    }

}
