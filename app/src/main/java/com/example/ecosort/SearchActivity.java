package com.example.ecosort;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity  extends AppCompatActivity {
    private Button scanBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        scanBtn = findViewById(R.id.scan_button);
        scanBtn.setOnClickListener(view -> {
            // go to scan page
            Intent intent = new Intent(SearchActivity.this, CameraScanActivity.class);
            startActivity(intent);
        });
    }
}
