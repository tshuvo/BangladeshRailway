package com.cns.bangladeshrailway.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager implements AppsConstant {

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private Context context;
    private static SharedPreferencesManager sharedPrefManager = null;

    private SharedPreferencesManager(Context context) {
        this.context = context;
        sharedPref = this.context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    public synchronized static SharedPreferencesManager getInstance(Context context) {
        if (sharedPrefManager == null) {
            sharedPrefManager = new SharedPreferencesManager(context);
        }
        return sharedPrefManager;
    }

    public boolean checkSession() {
        return sharedPref.getAll().size() >= 1;
    }

    public boolean saveToSession(String authCode, String deviceId) {
        sharedPrefClear();
        editor.putString(AUTH_CODE, authCode);
        editor.putString(DEVICE_ID, deviceId);
        return editor.commit();
    }

    public String getSharePrefString(String key, String defValue) {
        return sharedPref.getString(key, defValue);
    }

    public boolean getSharePrefBoolean(String key, boolean defValue) {
        return sharedPref.getBoolean(key, defValue);
    }

    public int getSharePrefInteger(String key, int defValue) {
        return sharedPref.getInt(key, defValue);
    }

    public long getSharePrefLong(String key, long defValue) {
        return sharedPref.getLong(key, defValue);
    }

    public float getSharePrefFloat(String key, float defValue) {
        return sharedPref.getFloat(key, defValue);
    }

    public boolean putSharePrefString(String key, String value) {
        editor.putString(key, value);
        return editor.commit();
    }

    public boolean putSharePrefBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public boolean putSharePrefInteger(String key, int value) {
        editor.putInt(key, value);
        return editor.commit();
    }

    public boolean putSharePrefFloat(String key, float value) {
        editor.putFloat(key, value);
        return editor.commit();
    }

    public boolean putSharePrefLong(String key, long value) {
        editor.putLong(key, value);
        return editor.commit();
    }

    public boolean removeKey(String key) {
        editor.remove(key);
        return editor.commit();
    }

    public boolean sharedPrefClear() {
        editor.clear();
        return editor.commit();
    }
}