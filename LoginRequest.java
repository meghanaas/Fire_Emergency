package com.example.pucchi.fire;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pucchi on 12-09-2017.
 */

public class LoginRequest extends AsyncTask<String,String,Boolean>
{ private Context context;

    Boolean error;
    //flag 0 means get and 1 means post.(By default it is get.)
    public LoginRequest(View.OnClickListener Context) {
        this.context = context;

    }

    protected void onPreExecute() {

    }

    protected Boolean doInBackground(String... arg0) {
        StringBuilder sb = new StringBuilder();
        try {
            String email = (String) arg0[0];
            String password = (String) arg0[1];
            String link = "https://viral-facts.000webhostapp.com/display.php";

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("email", email));
            params.add(new BasicNameValuePair("pass", password));


            ServiceHandler serviceClient = new ServiceHandler();


            String json = serviceClient.makeServiceCall(link,
                    ServiceHandler.POST, params);

            Log.d("Create Prediction Request: ", "> " + json);

            if (json != null) {
                try {
                    JSONObject jsonObj = new JSONObject(json);
                    //err=jsonObj.getString("error");
                    error = jsonObj.getBoolean("error");
                    // checking for error node in json
                    if (!error) {
                        // new category created successfully
                        Log.e("Prediction added successfully ", "> " + jsonObj.getString("message"));
                    } else {
                        Log.e("Add Prediction Error: ",
                                "> " + jsonObj.getString("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Log.e("JSON Data", "JSON data error!");
            }


        } catch (Exception e) {
            return null;
        }
        return error;
    }
}