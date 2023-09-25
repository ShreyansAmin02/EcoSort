package com.example.ecosort.Utils;

import android.content.SharedPreferences;

public class SharedPrefManager {

    private static SharedPreferences sharedPreferences = null;
    private static SharedPreferences getSharedPreference(){

        if (sharedPreferences == null){
            sharedPreferences = MyApp.getPreferences();
        }
        return sharedPreferences;

    }

    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USER_ROLE = "userRole";

    public static void setLoginState(boolean isLoggedIn){
        SharedPreferences.Editor editor = getSharedPreference().edit();
        // Saving login state
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn); // or false if not logged in
        editor.apply();
    }

    public static boolean isLoggedIn(){
        boolean isLoggedIn = getSharedPreference().getBoolean(KEY_IS_LOGGED_IN, false);
        return isLoggedIn;
    }

    public static void setAdmin(boolean isAdmin){
        SharedPreferences.Editor editor = getSharedPreference().edit();
        // Saving login state
        editor.putBoolean(KEY_USER_ROLE, isAdmin); // or false if not logged in
        editor.apply();
    }

    public static boolean isAdmin(){
        boolean isAdmin = getSharedPreference().getBoolean(KEY_USER_ROLE, false);
        return isAdmin;
    }

}