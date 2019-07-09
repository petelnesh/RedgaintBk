package com.redgiantbkke.techsavanna.redgiantbk.helper;

import com.redgiantbkke.techsavanna.redgiantbk.app.AppConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientStructure {

    public static Retrofit retrofit;

    public static Retrofit getApiStructure(){
        if (retrofit==null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConfig.SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
