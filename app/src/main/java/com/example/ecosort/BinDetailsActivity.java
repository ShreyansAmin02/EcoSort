package com.example.ecosort;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecosort.Utils.MyApp;
import com.example.ecosort.Utils.SharedPrefManager;

import DB.Bin;
import DB.BinDao;

public class BinDetailsActivity extends AppCompatActivity {
    private Button updateBTN;
    private Button deleteBTN;
    private Button reportBTN;
    private Button navBTN;
    private LinearLayout buttons;
    private LinearLayout buttons2;
    private TextView binType;
    private TextView binCapacity;
    private TextView binLocation;
    BinDao binDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setIds();
        binDao = MyApp.getAppDatabase().binDao();
        int binId;
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
        if (getIntent().getIntExtra("binId", -1) !=1 ){
            binId = getIntent().getIntExtra("binId", -1);
            binDao.getBinId(binId).observe(this, bin ->{
                binType.setText("Bin Type: "+ bin.getType());
                binCapacity.setText("Current Capacity: "+ bin.getCapacity());
                binLocation.setText("Bin Location: "+ bin.getLocation());
            });
        }
        else{
            Toast.makeText(this, "No Bin ID Found", Toast.LENGTH_SHORT).show();
        }

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
        binType = findViewById(R.id.tvBinType);
        binCapacity = findViewById(R.id.tvBinCurrentCapacity);
        binLocation = findViewById(R.id.tvBinLocation);

    }
}
