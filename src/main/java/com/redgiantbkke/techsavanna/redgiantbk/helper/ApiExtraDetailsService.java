package com.redgiantbkke.techsavanna.redgiantbk.helper;

import com.redgiantbkke.techsavanna.redgiantbk.methods.ShopExtra;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiExtraDetailsService {
    @FormUrlEncoded
    @POST("POST/extraShop.php")
    Call<ShopExtra> siteExpense(@Field("shopsize") String shopsize,
                                @Field("stock") String stock,
                                @Field("nameshop") String nameshop,
                                @Field("teleshop") String teleshop);
}
