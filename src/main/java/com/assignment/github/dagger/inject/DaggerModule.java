package com.assignment.github.dagger.inject;

import com.assignment.github.model.retrofit.RetroInterface;
import com.assignment.github.repository.BaseRepository;
import com.assignment.github.model.retrofit.API;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DaggerModule {

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return client;
    }
    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(API.URL)
                .client(okHttpClient)
                .build();
    }
    @Provides
    @Singleton
    RetroInterface provideApiInterface(Retrofit retrofit) {
        return retrofit.create(RetroInterface.class);
    }
    @Provides
    @Singleton
    BaseRepository provideMainRepo(RetroInterface retroInterface){
        return new BaseRepository(retroInterface);
    }
    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }
}
