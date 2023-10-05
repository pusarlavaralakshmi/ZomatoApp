package com.example.newapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.usb.UsbManager;
import android.media.MediaDataSource;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

public class RestaurtantAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] restaurtants;

    private String[] logos;



    public RestaurtantAdapter(Context context, String[] restaurtants, String[] logos) {
        super(context, R.layout.custom_restaurtant_name, restaurtants);
        this.context = context;
        this.restaurtants = restaurtants;
        this.logos = logos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertview, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View name = inflater.inflate(R.layout.custom_restaurtant_name, null, true);

        ImageView logoimg = name.findViewById(R.id.restaurtant_img);
        TextView restaurtantname = name.findViewById(R.id.restaurtant_name);

        restaurtantname.setText(restaurtants[position]);

        //new
        //picasso or glide for logoimg
        File file=new File(logos[position]).getAbsoluteFile();
        Picasso.get().load(file).into(logoimg);

        return name;
    }
}
