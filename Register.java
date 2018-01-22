package com.example.pucchi.fire;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText tname,temail,tphone,tpass,trepass;
    Button breg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tname=(EditText)findViewById(R.id.txtname);
        temail=(EditText)findViewById(R.id.txtemail);
        tphone=(EditText)findViewById(R.id.txtphone);
        tpass=(EditText)findViewById(R.id.txtpass);
        trepass=(EditText)findViewById(R.id.txtrepass);
        breg=(Button)findViewById(R.id.button);

        breg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*if(!(tpass.getText().equals(trepass.getText()) && trepass.getText().equals(tpass.getText()))) {*/
                    String name = tname.getText().toString();
                    String email = temail.getText().toString();
                    String no = tphone.getText().toString();
                    String pass = tpass.getText().toString();
                    String repass=trepass.getText().toString();

                    if(tname.length()==0)
                    {
                        tname.setError("Enter Name");
                    }
                else if(temail.length()==0)
                {
                    temail.setError("Enter Eamil Address");
                }
                    else if(tphone.length()==0)
                {
                    tphone.setError("Enter Phone Number");
                }
                    else if(tpass.length()==0)
                {
                    tpass.setError("Enter Password");
                }
                    else if(trepass.length()==0)
                {
                    trepass.setError("Enter Re-enter Password");
                }
                else if(pass.equals(repass))
                {
                    trepass.setError("Password Does not matches");
                    //tpass.setText("");
                    trepass.setText("");
                }
                else {
                    new RegisterRequest(this).execute(name, email, no, pass);


                    Toast.makeText(getApplicationContext(), "Registered Succefully", Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(Register.this, Login.class);


                    startActivity(it);

                }

            }
        });

    }

}
