package com.mobeyo.retrofithttppostsample.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Thomas on 10/28/2017.
 */

public interface APIService {
    //The register call
    @FormUrlEncoded
    @POST("createstudent")
    Call<Result> createUser(
            @Field("name") String name,
            @Field("username") String username,
            @Field("password") String password);
}
