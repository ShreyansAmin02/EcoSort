package com.example.ecosort;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.ecosort.Utils.SharedPrefManager;

public class BinDetailsActivity extends AppCompatActivity {
    private Button updateBTN;
    private Button deleteBTN;
    private Button reportBTN;
    private LinearLayout buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setIds();
        setContentView(R.layout.bin_details);
        manageRoleBasedFeatures();
        reportBTN.setOnClickListener(view ->{
            Intent intent = new Intent(BinDetailsActivity.this, ReportActivity.class);
            startActivity(intent);
        });
    }
    private void manageRoleBasedFeatures() {
        setIds();
        if (SharedPrefManager.isAdmin()) {
            buttons.setVisibility(View.VISIBLE);
            reportBTN.setVisibility(View.GONE);
        } else {
            buttons.setVisibility(View.GONE);
            reportBTN.setVisibility(View.VISIBLE);
        }
    }

    private void setIds(){
        updateBTN = findViewById(R.id.updateBtn);
        deleteBTN = findViewById(R.id.deleteBtn);
        buttons = findViewById(R.id.updelButtons);
        reportBTN = findViewById(R.id.reportBtn);
    }
}
