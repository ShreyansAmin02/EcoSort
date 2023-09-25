package com.example.ecosort;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecosort.Utils.SharedPrefManager;

public class ReportActivity extends AppCompatActivity {
    private Button uploadImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uploadImage = findViewById(R.id.uploadPhotoButton);
        setContentView(R.layout.report_activity);
//        uploadImage.setOnClickListener(view ->{
//            Intent intent = new Intent (ReportActivity.this, BinImageActivity.class);
//            startActivity(intent);
//        });
    }

}
