package com.example.ecosort;

import android.content.AsyncQueryHandler;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.text.AlteredCharSequence;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    Bin bin;
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
            binDao.getBinId(binId).observe(this, dbBin ->{
                binType.setText("Bin Type: "+ dbBin.getType());
                binCapacity.setText("Current Capacity: "+ dbBin.getCapacity());
                binLocation.setText("Bin Location: "+ dbBin.getLocation());
            });
        }
        else{
            Toast.makeText(this, "No Bin ID Found", Toast.LENGTH_SHORT).show();
        }
        updateBTN.setOnClickListener(view -> {
            Toast.makeText(this, "Update button pressed", Toast.LENGTH_SHORT).show();
            if (bin !=null ) {
                updateTheBin();
            }
        });
        deleteBTN.setOnClickListener(view -> {
            if (bin !=null){
                deleteTheBin();
            }

        });

    }
    private void updateTheBin(){
        View dialogView = getLayoutInflater().inflate(R.layout.add_bin, null);
        EditText editTextBinType = dialogView.findViewById(R.id.editTextBinType);
        EditText editTextBinCapacity = dialogView.findViewById(R.id.editTextBinCapacity);
        EditText editTextBinLocation = dialogView.findViewById(R.id.editTextBinLocation);

        editTextBinType.setText(bin.getType());
        editTextBinCapacity.setText(bin.getCapacity());
        editTextBinLocation.setText(bin.getLocation());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        builder.setPositiveButton("Update", (dialog, which) -> {
            String binType = editTextBinType.getText().toString();
            String binCapacity = editTextBinCapacity.getText().toString();
            String binLocation = editTextBinLocation.getText().toString();

            //update the parameters
            bin.setType(binType);
            bin.setCapacity(binCapacity);
            bin.setLocation(binLocation);

            AsyncTask.execute(()->{
                binDao.update(bin);
            });
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.cancel();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void deleteTheBin(){
        AsyncTask.execute(()->{
            binDao.delete(bin);
            finish();
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
        binType = findViewById(R.id.tvBinType);
        binCapacity = findViewById(R.id.tvBinCurrentCapacity);
        binLocation = findViewById(R.id.tvBinLocation);
    }
}
