package com.example.newapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mobilenumberEd, passwordEd;
    Button loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mobilenumberEd = findViewById(R.id.mobilenumber_edittext);
        passwordEd = findViewById(R.id.password_edittext);
        loginBtn = findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //logic
                String mobile = mobilenumberEd.getText().toString();
                String pass = passwordEd.getText().toString();

                if (!mobile.isEmpty()) {
                    //continue
                    if (!pass.isEmpty()) {
                        //continue

                        //login credentials check
                        if (mobile.length() == 10) {

                            Toast.makeText(MainActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                            //Intent
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            mobilenumberEd.setError("Must enter 10 digits");
                        }


                        Toast.makeText(MainActivity.this, "mobilenumber:" + mobile + "\n Password:" + pass, Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(MainActivity.this, "Password is required", Toast.LENGTH_SHORT).show();
                        passwordEd.setError("Password is Required");
                    }
                } else {
                    //empty
                    Toast.makeText(MainActivity.this, "mobilenumber is required", Toast.LENGTH_SHORT).show();
                    mobilenumberEd.setError("mobile number is required");
                }
            }
        });
    }
    public void openAdmin(View view) {
         Intent intent = new Intent(MainActivity.this,AdminActivity.class);
         startActivity(intent);
     }

}








