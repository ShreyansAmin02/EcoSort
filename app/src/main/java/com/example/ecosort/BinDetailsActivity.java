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
    private Button navBTN;
    private LinearLayout buttons;
    private LinearLayout buttons2;

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
        navBTN.setOnClickListener(view -> {
            Intent intent = new Intent (BinDetailsActivity.this, NavigationActivity.class);
            startActivity(intent);
        });

    }
    private void manageRoleBasedFeatures() {
        setIds();
        if (SharedPrefManager.isAdmin()) {
            buttons.setVisibility(View.VISIBLE);
            buttons2.setVisibility(View.GONE);
        } else {
            buttons.setVisibility(View.GONE);
            buttons2.setVisibility(View.VISIBLE);
        }
    }

    private void setIds(){
        updateBTN = findViewById(R.id.updateBtn);
        deleteBTN = findViewById(R.id.deleteBtn);
        buttons = findViewById(R.id.updelButtons);
        reportBTN = findViewById(R.id.reportBtn);
        navBTN = findViewById(R.id.navBtn);
        buttons2 = findViewById(R.id.reportNavBtns);

    }
}
