package com.boldijarpaul.polihack.prefs;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;


import com.boldijarpaul.polihack.R;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PrefsModule {

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Application application) {
        return application.getSharedPreferences(application.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    @UserLocationLatitude
    FloatPreference provideUserLocationLatitude(SharedPreferences sharedPreferences) {
        return new FloatPreference(sharedPreferences, "user_location_latitude", -1);
    }

    @Provides
    @Singleton
    @UserLocationLongitude
    FloatPreference provideUserLocationLongitude(SharedPreferences sharedPreferences) {
        return new FloatPreference(sharedPreferences, "user_location_longitude", -1);
    }

    @Provides
    @Singleton
    CurrentUserPreference provideUserPreference(SharedPreferences sharedPreferences) {
        return new CurrentUserPreference(sharedPreferences, "user_location_longitude");
    }

}
