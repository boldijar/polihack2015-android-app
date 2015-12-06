package com.boldijarpaul.polihack.dagger;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;

import com.boldijarpaul.polihack.mvp.model.DatabaseHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

@Module(includes = {ApiModule.class})
public class DataModule {

    private static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB

    @Provides
    @Singleton
    DatabaseHandler provideDbHandler() {
        return new DatabaseHandler();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Application app) {
        OkHttpClient client = new OkHttpClient();
        // Install an HTTP cache in the application cache directory.
        File cacheDir = new File(app.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
        client.setCache(cache);
        client.setConnectTimeout(15, TimeUnit.SECONDS);
        client.setReadTimeout(30, TimeUnit.SECONDS);

        return client;
    }

    @Provides
    @Singleton
    Picasso providePicasso(Application app, OkHttpClient client) {
        return new Picasso.Builder(app)
                .downloader(new OkHttpDownloader(client))
                .listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception e) {
                        Timber.i(e, "Failed to load image: %s", uri);
                    }
                })
                .build();
    }

    @Provides
    ConnectivityManager provideConnectivityManager(Application app) {
        return (ConnectivityManager) app.getSystemService(Context.CONNECTIVITY_SERVICE);
    }


    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss 'T'ZZZZZ")
                .create();
    }


}
