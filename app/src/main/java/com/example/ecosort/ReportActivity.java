package com.example.ecosort;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import DB.Database; // Assuming your Database class is in a package named "DB"

public class ReportActivity extends Activity {

    private EditText problemTypeEditText;
    private EditText locationEditText;
    private EditText descriptionEditText;
    private ImageButton uploadPhotoButton;
    private Button submitButton;
    private Database dbHelper; // Use your custom Database class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_activity);

        // Initialize your views
        problemTypeEditText = findViewById(R.id.problemTypeEditText);
        locationEditText = findViewById(R.id.locationEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        uploadPhotoButton = findViewById(R.id.uploadPhotoButton);
        submitButton = findViewById(R.id.submitButton);

        // Initialize the DatabaseHelper
        dbHelper = new Database(this);

        // Set click listeners or perform actions for your buttons
        uploadPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the camera when the button is clicked
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add code to handle the submit button click and upload data to the database
                uploadDataToDatabase();
            }
        });
    }

    private void uploadDataToDatabase() {
        // Get data from EditText fields
        String problemType = problemTypeEditText.getText().toString();
        String location = locationEditText.getText().toString();
        String description = descriptionEditText.getText().toString();

        // Open a writable database instance
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Insert data into the database
        ContentValues values = new ContentValues();
        values.put("problemType", problemType);
        values.put("location", location);
        values.put("description", description);

        // Insert the data into the table
        long newRowId = db.insert("issueTable", null, values);

        // Close the database connection
        db.close();

        // Handle the result of the database insertion
        if (newRowId != -1) {
            // Data insertion successful
            showToast("Data inserted successfully");
            // You can perform additional actions here, such as navigating to another activity
        } else {
            // Data insertion failed
            showToast("Error inserting data");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
