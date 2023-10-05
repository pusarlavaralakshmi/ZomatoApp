package com.example.newapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FoodItemsActivity extends AppCompatActivity {
    ListView fooditemslistview;

    TextView RestaurtantName;

    String[] FoodItems = {""};

    int[] logosint;

    Integer[] Logos={};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_items);
        //set ids
        fooditemslistview = findViewById(R.id.fooditems_listview);
        RestaurtantName = findViewById(R.id.name_tv);

        //receive the data
        FoodItems = getIntent().getStringArrayExtra("FoodItems");
        String restaurtantName = getIntent().getStringExtra("RestaurtantName");
        logosint=getIntent().getIntArrayExtra("Logos");
        Logos=toConvertInteger(logosint);

        //Adapter
       // ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, FoodItems);
       // RestaurtantAdapter adapter=new RestaurtantAdapter(this,FoodItems,Logos);
        //fooditemslistview.setAdapter(adapter);

        RestaurtantName.setText(restaurtantName);

    }
    public static Integer[] toConvertInteger(int[] ids) {

    Integer[] newArray = new Integer[ids.length];
    for(int i=0; i< ids.length; i++){
        newArray[i] = Integer.valueOf(ids[i]);
    }
    return newArray;
}
}



