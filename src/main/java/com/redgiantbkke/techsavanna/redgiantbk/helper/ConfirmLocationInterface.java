package com.redgiantbkke.techsavanna.redgiantbk.helper;

import com.redgiantbkke.techsavanna.redgiantbk.methods.SendStructure;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ConfirmLocationInterface {
    @FormUrlEncoded
    @POST("locationPost.php")
    Call<SendStructure> locationPost(@Field("structurename") String structurename,
                                     @Field("telephone") String telephone,
                                     @Field("lat") String lat,
                                     @Field("lon") String lon);
}
