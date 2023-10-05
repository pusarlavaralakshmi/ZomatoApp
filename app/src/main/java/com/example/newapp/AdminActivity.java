package com.example.newapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {

    Button CatAddBtn, CatUpdateBtn, SubCatAddBtn, SubCatUpdateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        CatAddBtn = findViewById(R.id.cat_add_btn);
        CatUpdateBtn = findViewById(R.id.cat_update_btn);
        SubCatAddBtn = findViewById(R.id.subcat_add_btn);
        SubCatUpdateBtn = findViewById(R.id.subcat_update_btn);


        CatAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCategoryOperation();

            }
        });
        CatUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openCategoryOperation();
            }
        });
        SubCatAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openSubCategoryOperation();
            }
        });
        SubCatUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openSubCategoryOperation();
            }
        });

    }

    private void openSubCategoryOperation() {
        Intent intent = new Intent(AdminActivity.this, CrudActivity.class);
        intent.putExtra("purpose", "subcategory");
        startActivity(intent);
    }

    private void openCategoryOperation() {
        Intent intent = new Intent(AdminActivity.this, CrudActivity.class);
        intent.putExtra("purpose", "category");
        startActivity(intent);
    }
}