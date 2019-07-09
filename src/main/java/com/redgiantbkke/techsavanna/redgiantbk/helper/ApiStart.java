package com.redgiantbkke.techsavanna.redgiantbk.helper;

import com.redgiantbkke.techsavanna.redgiantbk.app.AppConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiStart {

    public static Retrofit retrofit;

    public static Retrofit getApiClient(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (retrofit==null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConfig.SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
