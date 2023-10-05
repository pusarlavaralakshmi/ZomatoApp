package com.example.newapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button listBtn;
    Button customlistviewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listBtn = findViewById(R.id.list_btn);
        customlistviewBtn=findViewById(R.id.customlistview_btn);


        listBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               Intent intent=new Intent(HomeActivity.this, ListActivity.class);
                startActivity(intent);

            }

            });
          customlistviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,RestaurtantActivity.class);
                startActivity(intent);
            }
        });



    };
            }


