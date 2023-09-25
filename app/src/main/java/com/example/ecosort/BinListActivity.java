package com.example.ecosort;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecosort.Utils.SharedPrefManager;

import java.util.ArrayList;

public class BinListActivity extends AppCompatActivity {
    ArrayList<Bin> binList;
    private RecyclerView recyclerView;
    private Button btnAddNewBin;
    private Button searchBtn;
    private Button scheduleBtn;
    private BinAdapter binAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bin_list);
        setIds();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        binList = generateDummyData();

        binAdapter = new BinAdapter(binList);
        recyclerView.setAdapter(binAdapter);
        btnAddNewBin.setOnClickListener(view -> { manageNewBinFunctionality(); });
        searchBtn.setOnClickListener(view -> {
            //Go to search view
            Intent intent = new Intent(BinListActivity.this, SearchActivity.class);
            startActivity(intent);

        });
        scheduleBtn.setOnClickListener(view -> {
            //Go to search view
            Intent intent = new Intent(BinListActivity.this, ScheduleActivity.class);
            startActivity(intent);

        });
        binAdapter.setOnItemClickListener(position -> {
            Toast.makeText(BinListActivity.this,
                    binList.get(position).getType(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(BinListActivity.this, BinDetailsActivity.class);
            startActivity(intent);
        });
        manageRoleBasedFeatures();
    }
    private void manageRoleBasedFeatures() {
        if (SharedPrefManager.isAdmin()) {
            btnAddNewBin.setVisibility(View.VISIBLE);
            btnAddNewBin.setOnClickListener(view -> {
                manageNewBinFunctionality();
            });
        } else {
            btnAddNewBin.setVisibility(View.GONE);
        }
    }


    private void manageNewBinFunctionality() {

        AlertDialog.Builder builder = new AlertDialog.Builder(BinListActivity.this);
        View dialogView = getLayoutInflater().inflate(R.layout.add_bin, null);
        builder.setView(dialogView);

        EditText editTextBinType = dialogView.findViewById(R.id.editTextBinType);
        EditText editTextBinCapacity = dialogView.findViewById(R.id.editTextBinCapacity);
        EditText editTextBinLocation = dialogView.findViewById(R.id.editTextBinLocation);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String binType = editTextBinType.getText().toString();
            String binCapacity = editTextBinCapacity.getText().toString();
            String binLocation = editTextBinLocation.getText().toString();

            // Add the new vehicle to the list
            Bin bin = new Bin(binType, binLocation, binCapacity);
            binList.add(0, bin);
            binAdapter.notifyDataSetChanged();
        });

        AlertDialog dialog = builder.create();
        builder.setNegativeButton("Cancel", null);
        dialog.show();

    }
    private ArrayList<Bin> generateDummyData() {
        ArrayList<Bin> binList = new ArrayList<>();
        binList.add(new Bin ("Recycle", "Westfield Mt Gravatt", "250"));
        binList.add(new Bin ("General", "Westfield Mt Gravatt", "250"));
        binList.add(new Bin ("General", "Bombay Dhaba", "250"));
        binList.add(new Bin ("Recycle", "Bombay Dhaba", "250"));
        // Add more vehicles as needed
        return binList;
    }
    private void setIds(){
        recyclerView = findViewById(R.id.recyclerView);
        btnAddNewBin = findViewById(R.id.btnAddBin);
        searchBtn = findViewById(R.id.searchButton);
        scheduleBtn = findViewById(R.id.scheduleButton);
    }
}