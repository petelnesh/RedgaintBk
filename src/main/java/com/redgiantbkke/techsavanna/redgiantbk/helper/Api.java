package com.redgiantbkke.techsavanna.redgiantbk.helper;

import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public class Api {
    private static final String ENDPOINT = "http://192.168.100.208:8080/api/";
    private static final String API_UPLOAD = "uploadImage";

    private UploadService uploadService;

    public Api(){
        Retrofit api;

        api = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        uploadService = api.create(UploadService.class);
    }

    private interface UploadService{
        @Multipart
        @POST(API_UPLOAD)
        Call<JsonObject> uploadImage(@Part MultipartBody.Part image);
    }

    public Call<JsonObject> uploadImage(MultipartBody.Part image){
        return uploadService.uploadImage(image);
    }
}
