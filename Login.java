package com.example.client.fire;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class Login extends Activity {

    private int RECORD_AUDIO_REQUEST_CODE =123 ;
    Boolean b;
    TextView treg;
    EditText tmail,tpass;
    Button blog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tmail=(EditText)findViewById(R.id.txtlogmail);
        tpass=(EditText)findViewById(R.id.txtlogpass);
        blog=(Button)findViewById(R.id.login);
        treg=(TextView)findViewById(R.id.txtreg);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getPermissionToRecordAudio();
        }


        blog.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {

                String email=tmail.getText().toString();
                String password=tpass.getText().toString();


                if(email.length()==0)
                {
                    tmail.setError( "Enter Email ID");

                }
                else if(password.length()==0){
                    tpass.setError("Enter Password");

                }
                else {
                    try {
                        b = new LoginRequest(this).execute(email, password).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }


                    if (!b) {
                        //Toast.makeText(getApplicationContext(), "Welcome " + tmail, Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(Login.this, MainActivity.class);
                        // String name = (String) treg.getText();
                        //it.putExtra("loc", name);
                        startActivity(it);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Incorrect details.Please enter correct details",Toast.LENGTH_LONG).show();
                    }
                }
                }

        });

        treg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Login.this,Register.class);
                startActivity(it);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getPermissionToRecordAudio() {
        // 1) Use the support library version ContextCompat.checkSelfPermission(...) to avoid
        // checking the build version since Context.checkSelfPermission(...) is only available
        // in Marshmallow
        // 2) Always check for permission (even if permission has already been granted)
        // since the user can revoke permissions at any time through Settings


        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},RECORD_AUDIO_REQUEST_CODE);
        }

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},RECORD_AUDIO_REQUEST_CODE);
        }

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS)!=PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.SEND_SMS},RECORD_AUDIO_REQUEST_CODE);
        }

    }


}
