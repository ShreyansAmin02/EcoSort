package com.example.ecosort.Utils;

import android.app.Application;
import android.content.SharedPreferences;
import com.example.ecosort.MainActivity;
import com.example.ecosort.BinListActivity;
public class MyApp extends Application {

    public static final String PREF_NAME = "MyAppPrefs";
    private static SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
    }

    public static SharedPreferences getPreferences() {
        return preferences;
    }
}