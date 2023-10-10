package com.example.ecosort.Utils;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.room.Room;

import com.example.ecosort.MainActivity;
import com.example.ecosort.BinListActivity;

import DB.AppDatabase;

public class MyApp extends Application {

    public static final String PREF_NAME = "MyAppPrefs";
    private static SharedPreferences preferences;
    private static AppDatabase appDatabase;
    private static String DATABASE_NAME = "ECOSORT_DATABASE";

    @Override
    public void onCreate() {
        super.onCreate();
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, DATABASE_NAME).build();
        preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
    }
    public static AppDatabase getAppDatabase(){
        return appDatabase;
    }

    public static SharedPreferences getPreferences() {
        return preferences;
    }
}