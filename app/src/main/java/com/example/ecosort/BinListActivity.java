package com.example.ecosort;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecosort.Utils.MyApp;
import com.example.ecosort.Utils.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import DB.Bin;
import DB.BinDao;

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

        binList = new ArrayList<>();
        getDataFromDatabase();

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
            Bin bin = binList.get(position);
            intent.putExtra("binId", bin.id);
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
        String[] binTypes = {"General", "Recycle", "Glass", "Green Waste"};

        AutoCompleteTextView autoCompleteTextView;

        ArrayAdapter<String> adapterItems;

        AlertDialog.Builder builder = new AlertDialog.Builder(BinListActivity.this);
        View dialogView = getLayoutInflater().inflate(R.layout.add_bin, null);
        builder.setView(dialogView);

        EditText editTextBinType = dialogView.findViewById(R.id.editTextBinType);
        EditText editTextBinCapacity = dialogView.findViewById(R.id.editTextBinCapacity);
        EditText editTextBinLocation = dialogView.findViewById(R.id.editTextBinLocation);
        autoCompleteTextView = dialogView.findViewById(R.id.binTypeDrop);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, binTypes);
        autoCompleteTextView.setAdapter(adapterItems);


        builder.setPositiveButton("Add", (dialog, which) -> {
//            This is so that we can use a drop down menu instead, but it's not working yet:
//            autoCompleteTextView.setOnClickListener(new AdapterView.OnItemClickListener(){
//                @Override
//                public void onItemClickListener(AdapterView<?> adapterView, View view, int position, long id) {
//                    String binType = adapterView.getItemAtPosition(position).toString();
//                }
//            });
            String binType = editTextBinType.getText().toString();
            String binCapacity = editTextBinCapacity.getText().toString();
            String binLocation = editTextBinLocation.getText().toString();

            // Add the new bin to the list
            Bin bin = new Bin(binType, binLocation, binCapacity);
            BinDao binDao = MyApp.getAppDatabase().binDao();
            AsyncTask.execute(() ->{
                binDao.insert(bin);
            });
            binAdapter.notifyDataSetChanged();
        });

        AlertDialog dialog = builder.create();
        builder.setNegativeButton("Cancel", null);
        dialog.show();

    }

    private void getDataFromDatabase(){
        BinDao binDao = MyApp.getAppDatabase().binDao();
        LiveData<List<Bin>> binsLiveData = binDao.getAllBins();
        binsLiveData.observe(this, bins -> {
            if (bins.size() > 0) {
                binList.clear();
                binList.addAll(bins);
                binAdapter.notifyDataSetChanged();
            }
            else{
                Toast.makeText(this, "No bins found in database", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setIds(){
        recyclerView = findViewById(R.id.recyclerView);
        btnAddNewBin = findViewById(R.id.btnAddBin);
        searchBtn = findViewById(R.id.searchButton);
        scheduleBtn = findViewById(R.id.scheduleButton);
    }
}