package com.example.ecosort;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecosort.Utils.SharedPrefManager;

public class ReportActivity extends AppCompatActivity {
    private ImageButton uploadImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setIds();
        setContentView(R.layout.report_activity);
        uploadImage.setOnClickListener(view ->{
            //go to image

        });
    }

    private void setIds(){
        uploadImage = findViewById(R.id.uploadPhotoButton);
    }
}
