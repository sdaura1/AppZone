package com.example.prozone.network;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.auth0.android.jwt.JWT;

public class SharedPreferenceManager {
    private static final String JWT_KEY_USERNAME = "username";
    private SharedPreferences prefs;
    private static final String PREFS = "prefs";
    private static final String PREF_TOKEN = "pref_token";
    private static SharedPreferenceManager Instance =  null;

    private SharedPreferenceManager(@NonNull Context context){
        this.prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        this.Instance = this;
    }

    public static SharedPreferenceManager getInstance(@NonNull Context context){
        if (Instance == null){
            Instance = new SharedPreferenceManager(context);
        }
        return Instance;
    }

    public void deleteToken(){
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(PREF_TOKEN);
        editor.apply();
    }

    public String getToken(){
        return prefs.getString(PREF_TOKEN, null);
    }

    public boolean isLoggedIn() {
        String token = getToken();
        return token != null;
    }

    public String getUsername() {
        if (isLoggedIn()) {
            return decodeUsername(getToken());
        }
        return null;
    }

    @Nullable
    private String decodeUsername(String token) {
        JWT jwt = new JWT(token);
        try {
            if (jwt.getClaim(JWT_KEY_USERNAME) != null) {
                return jwt.getClaim(JWT_KEY_USERNAME).asString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void clear() {
        prefs.edit().clear().apply();
    }
}
