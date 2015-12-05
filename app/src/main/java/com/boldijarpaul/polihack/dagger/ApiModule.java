package com.boldijarpaul.polihack.dagger;

import com.boldijarpaul.polihack.BuildConfig;
import com.boldijarpaul.polihack.service.UserService;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

@Module
public class ApiModule {

    private static final String API_APP_DATA_ENDPOINT = "http://polihack-boldijar.rhcloud.com/api/v1/";

    @Provides
    @Singleton
    Endpoint provideAppDataEndpoint() {
        return Endpoints.newFixedEndpoint(API_APP_DATA_ENDPOINT);
    }

    @Provides
    @Singleton
    Client provideClient(OkHttpClient client) {
        return new OkClient(client);
    }

    @Provides
    @Singleton
    Converter provideConverter(Gson gson) {
        return new GsonConverter(gson);
    }

    @Provides
    @Singleton
    RestAdapter provideAppDataRestAdapter(Endpoint endpoint,
                                          Client client,
                                          Converter converter) {
        return new RestAdapter.Builder()
                .setClient(client)
                .setEndpoint(endpoint)
                .setConverter(converter)
                .setLogLevel(
                        BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .build();
    }

    @Provides
    @Singleton
    UserService provideUserService(RestAdapter restAdapter) {
        return restAdapter.create(UserService.class);
    }


}
