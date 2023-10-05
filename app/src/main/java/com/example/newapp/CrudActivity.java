package com.example.newapp;

import static android.widget.Toast.makeText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;


public class CrudActivity extends AppCompatActivity {

    TextView purposeTv;

    EditText idEd, nameEd, statusEd;
    Button addBtn, deleteBtn, updateBtn, readBtn, uploadBtn;
    ImageView previewImageView;
    RestaurtantDataSqlite restaurtantDataSqlite;
    private final static int pic_id = 1432;

    String addressUri = "";
    String Pathaddress = "";
    Spinner CatSpinner, SubCatSpinner;

    String[] restaurtant_names = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);
        purposeTv = findViewById(R.id.purpose_tv);
        CatSpinner = findViewById(R.id.category_spinner);
        SubCatSpinner = findViewById(R.id.subcategory_spinner);
        restaurtantDataSqlite = new RestaurtantDataSqlite(this);

        idEd = findViewById(R.id.id_edittext);
        nameEd = findViewById(R.id.name_edittext);
        statusEd = findViewById(R.id.status_edittext);

        addBtn = findViewById(R.id.add_btn);
        deleteBtn = findViewById(R.id.delete_btn);
        updateBtn = findViewById(R.id.update_btn);
        readBtn = findViewById(R.id.read_btn);
        uploadBtn = findViewById(R.id.uploadimage_btn);
        previewImageView = findViewById(R.id.preview_imageview);

        String purpose = getIntent().getStringExtra("purpose");
        purposeTv.setText(purpose);

        if (purpose.equals("subcategory")) {
            CatSpinner.setVisibility(View.VISIBLE);
            idEd.setEnabled(false);
        }
        //spinner
        String condition = null;
        ArrayList<String> arrayList = new ArrayList<String>();
        ArrayList<String> categoryIDList = new ArrayList<String>();
        Cursor result = restaurtantDataSqlite.listData("category", condition);
        if (result.getCount() == 0) {
            arrayList.add("No data");
        }
        while (result.moveToNext()) {
            arrayList.add(result.getString(1));
            categoryIDList.add(result.getString(0));
        }
        //name string array
        restaurtant_names = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            restaurtant_names[i] = arrayList.get(i);
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, restaurtant_names);
        CatSpinner.setAdapter(adapter);
        CatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int position = i;
                idEd.setText(categoryIDList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //end of the spinner

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEd.getText().toString();
                Integer status = Integer.parseInt(statusEd.getText().toString());

                String id = idEd.getText().toString();
                boolean isInserted = restaurtantDataSqlite.insertData(purpose, id, name, Pathaddress, status);
                if (isInserted) {
                    makeText(CrudActivity.this, " " + purpose + " Added Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CrudActivity.this, CrudActivity.class);
                    intent.putExtra("purpose", "purpose");
                    startActivity(intent);
                } else {
                    makeText(CrudActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //implicit intent
                Intent Open_camera = new Intent();
                Open_camera.setType("image/*");
                Open_camera.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(Open_camera, "Selected image"), pic_id);
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEd.getText().toString();
                Integer status = Integer.parseInt(statusEd.getText().toString());
                String id = idEd.getText().toString();

                boolean isUpdated = restaurtantDataSqlite.updateData(id, purpose, name, Pathaddress, status);
                if (isUpdated) {
                    makeText(CrudActivity.this, " " + purpose + " Updated Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CrudActivity.this, CrudActivity.class);
                    intent.putExtra("purpose", "purpose");
                    startActivity(intent);
                } else {
                    makeText(CrudActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idEd.getText().toString();
                Integer isDeleted = restaurtantDataSqlite.deleteData(id, purpose);
                if (isDeleted > 0) {
                    Toast.makeText(CrudActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CrudActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == pic_id) {
            Uri selectedimg = data.getData();
            previewImageView.setImageURI(selectedimg);
            addressUri = data.getData().toString();
            //new
            Uri uri = data.getData();
            File file = new File(uri.getPath());//create path from uri
            final String[] split = file.getPath().split(":");//split the path.
            String filePath = split[1];//assign it to a string(your choice).

            Log.i("path Image", filePath);
            Pathaddress = filePath;
        }
    }
}






