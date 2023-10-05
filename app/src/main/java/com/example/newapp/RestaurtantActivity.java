package com.example.newapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RestaurtantActivity extends AppCompatActivity {

    String[] restaurtant_names={};
    String[] logos={};
    ListView restaurtantlistView;

    RestaurtantDataSqlite restaurtantDataSqlite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurtant);
        restaurtantDataSqlite=new RestaurtantDataSqlite(this);
        //set ids
        restaurtantlistView = findViewById(R.id.listview);
        String purpose="category";

        readCategory(purpose);


        RestaurtantAdapter adapter = new RestaurtantAdapter(this, restaurtant_names, logos);
        restaurtantlistView.setAdapter(adapter);


        restaurtantlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int position = i;
                String restaurtantselected = restaurtantlistView.getItemAtPosition(position).toString();
                Toast.makeText(RestaurtantActivity.this, "Selected:" + restaurtantselected, Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void readCategory(String purpose) {
        String condition=null;
        Cursor result = restaurtantDataSqlite.listData(purpose,condition);
        if (result.getCount()==0) {
            displayMessage("data", "No Data Found");
        }
        ArrayList<String> arrayList = new ArrayList<String>();
        ArrayList<String> logoarray = new ArrayList<>();
        while (result.moveToNext()) {
            arrayList.add(result.getString(1));
            logoarray.add(result.getString(2));
            Log.i("images db: ", result.getString(2));

        }
        //name string array
        restaurtant_names=new String[arrayList.size()];
        for (int i = 0; i <arrayList.size(); i++) {
            restaurtant_names[i] = arrayList.get(i);
        }
        //logo string array
        logos=new String[logoarray.size()];
        for (int i = 0; i < logoarray.size(); i++) {
            logos[i] = logoarray.get(i);
            Log.i("image address: "+i, logoarray.get(i));
        }
    }
        private void displayMessage(String data, String message){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.create();
            builder.setCancelable(true);
            builder.setTitle(data);
            builder.setMessage(message);
            builder.show();
        }


        public static int[] toint(Integer[] WrapperArray){
            int[] newArray = new int[WrapperArray.length];
            for (int i = 0; i < WrapperArray.length; i++) {
                newArray[i] = WrapperArray[i].intValue();
            }
            return newArray;
        }
    }







