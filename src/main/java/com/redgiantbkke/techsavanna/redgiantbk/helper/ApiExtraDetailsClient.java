package com.redgiantbkke.techsavanna.redgiantbk.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.redgiantbkke.techsavanna.redgiantbk.app.AppConfig.SERVER_URL;

public class ApiExtraDetailsClient {
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();


            retrofit = new Retrofit.Builder()
                    .baseUrl(SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
