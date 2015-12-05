package com.boldijarpaul.polihack.prefs;

import android.content.SharedPreferences;

import com.boldijarpaul.polihack.mvp.model.User;
import com.google.gson.Gson;

public class CurrentUserPreference {

    private final SharedPreferences mPreferences;
    private final String mKey;

    private static Gson sGson = new Gson();


    public CurrentUserPreference(SharedPreferences preferences, String key) {
        this.mPreferences = preferences;
        this.mKey = key;
    }

    public User get() {
        String json = mPreferences.getString(mKey, null);
        try {
            return sGson.fromJson(json, User.class);
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean isSet() {
        return mPreferences.contains(mKey);
    }

    public void set(User value) {
        mPreferences.edit().putString(mKey, sGson.toJson(value)).apply();
    }

    public void delete() {
        mPreferences.edit().remove(mKey).apply();
    }
}
