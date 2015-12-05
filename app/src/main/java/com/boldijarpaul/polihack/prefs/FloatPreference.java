package com.boldijarpaul.polihack.prefs;

import android.content.SharedPreferences;


public class FloatPreference {

    private final SharedPreferences mPreferences;
    private final String mKey;
    private final int mDefaultValue;

    public FloatPreference(SharedPreferences preferences, String key) {
        this(preferences, key, 0);
    }

    public FloatPreference(SharedPreferences preferences, String key, int defaultValue) {
        this.mPreferences = preferences;
        this.mKey = key;
        this.mDefaultValue = defaultValue;
    }

    public float get() {
        return mPreferences.getFloat(mKey, mDefaultValue);
    }

    public boolean isSet() {
        return mPreferences.contains(mKey);
    }

    public void set(float value) {
        mPreferences.edit().putFloat(mKey, value).apply();
    }

    public void delete() {
        mPreferences.edit().remove(mKey).apply();
    }

}
