package com.example.pucchi.fire;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.pucchi.*;

//import com.example.pucchi.v_resq.Model.User;
//import com.example.pucchi.v_resq.sql.DBHelper;

public class splash extends AppCompatActivity {

    DBHelper mydb;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        mydb = new DBHelper(this);
        int name=mydb.getCount();
        //verifyFromSQLite();
        if (name!=0) {
            Intent intent = new Intent(splash.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {


            Thread timerThread = new Thread() {
                public void run() {
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        finish();
                        Intent intent = new Intent(splash.this, Login.class);
                        startActivity(intent);
                    }
                }
            };
            timerThread.start();


          /* @Override
            protected void onPause(){
                // TODO Auto-generated method stub
                super.onPause();
                finish();
            }*/
        }
    }

}
