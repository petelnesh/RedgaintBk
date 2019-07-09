package com.redgiantbkke.techsavanna.redgiantbk.helper;

import com.redgiantbkke.techsavanna.redgiantbk.methods.Start;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterfaceStart {

    @GET("verify.php")
    Call<List<Start>> getDeviceid(
            @Query("deviceid") String deviceid
    );
}
