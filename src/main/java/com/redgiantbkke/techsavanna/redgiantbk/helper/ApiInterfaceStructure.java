package com.redgiantbkke.techsavanna.redgiantbk.helper;

import com.redgiantbkke.techsavanna.redgiantbk.methods.Structure;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterfaceStructure {

    @GET("getStructures.php")
    Call<List<Structure>> getStructure(
            @Query("item_type") String item_type,
            @Query("structure") String keyword
    );
}
