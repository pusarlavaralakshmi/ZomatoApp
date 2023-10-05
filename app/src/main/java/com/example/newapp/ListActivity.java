package com.example.newapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {

    String[] restaurtant_names={"Paradise","Zeeshan","Eaters Stop","Best Western Ramachandra","Punjab Dhaba","Helapuri"," Hotel Alif","Coastal Grand In","Inbox","Muntaj","Grand Alpha","The Invitation 365"};

    ListView restaurtantlistview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //set ids
        restaurtantlistview=findViewById(R.id.listview);

        //Adapter
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,restaurtant_names);
        restaurtantlistview.setAdapter(adapter);

    }

}