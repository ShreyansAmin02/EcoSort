package com.example.ecosort;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CameraScanActivity extends AppCompatActivity {

    private ImageView screen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_scan);
        screen = findViewById(R.id.camera);
        screen.setOnClickListener(view -> {
            // go to nearest page
            Intent intent = new Intent(CameraScanActivity.this, NearestActivity.class);
            startActivity(intent);

        });
    }
}
