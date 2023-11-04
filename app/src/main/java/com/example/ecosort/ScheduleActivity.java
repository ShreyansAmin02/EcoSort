package com.example.ecosort;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import DB.DB;

public class ScheduleActivity extends AppCompatActivity {

    private TextView dateTextView, timeTextView;
    private EditText locationEditText;
    private RadioGroup scheduleRadioGroup;
    private Button submitButton;
    private Calendar calendar;
    private DB databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_activity);

        databaseHelper = new DB(this);
        dateTextView = findViewById(R.id.EditText1);
        timeTextView = findViewById(R.id.EditText2);
        locationEditText = findViewById(R.id.locationEditText);
        scheduleRadioGroup = findViewById(R.id.scheduleRadioGroup);
        submitButton = findViewById(R.id.submitButton);

        calendar = Calendar.getInstance();

        // Date Picker Dialog
        Button datePickerButton = findViewById(R.id.datePickerButton);
        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Time Picker Dialog
        Button timePickerButton = findViewById(R.id.timePickerButton);
        timePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        // Submit Button Click Listener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = dateTextView.getText().toString();
                String time = timeTextView.getText().toString();
                String location = locationEditText.getText().toString();
                String scheduleType = getSelectedScheduleType();

                // Insert data into the database
                databaseHelper.insertData(date, time, location, scheduleType);

                // Display a toast message indicating success
                showToast("Scheduled");
            }
        });
    }

    private void showDatePickerDialog() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                        dateTextView.setText(sdfDate.format(calendar.getTime()));
                    }
                },
                year, month, dayOfMonth
        );
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm a", Locale.US);
                        timeTextView.setText(sdfTime.format(calendar.getTime()));
                    }
                },
                hourOfDay, minute, false
        );
        timePickerDialog.show();
    }

    private String getSelectedScheduleType() {
        int selectedId = scheduleRadioGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton radioButton = findViewById(selectedId);
            return radioButton.getText().toString();
        }
        return "";
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}