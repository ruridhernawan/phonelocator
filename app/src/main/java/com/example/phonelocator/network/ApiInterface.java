package com.example.phonelocator.network;

import com.example.phonelocator.model.PostModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("/checklogin")
    Call<PostModel> postData(@Body PostModel postModel, @Header ("key") String key);
}
