package com.example.phonelocator.network;

import com.example.phonelocator.model.LastTimeGpsModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiLastTimeGps {
    @POST("/inquirylasttimegps")
    Call<LastTimeGpsModel> postData(@Body LastTimeGpsModel lastTimeGpsModel, @Header ("key") String key);
}
