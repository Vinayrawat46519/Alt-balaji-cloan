package com.seeksolution.altbalaji.Api;

import com.seeksolution.altbalaji.Model.CreateUserResponce;
import com.seeksolution.altbalaji.Model.LoginResponse;
import com.seeksolution.altbalaji.Model.OriginalsModelRespoce;
import com.seeksolution.altbalaji.Model.SliderModelResponse;
import com.seeksolution.altbalaji.Model.SubscriptionPackageResponse;
import com.seeksolution.altbalaji.Model.UpdatePackageResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @GET("user")
    Call<OriginalsModelRespoce> getMovies();
     @FormUrlEncoded
    @POST("user")
    Call<CreateUserResponce> createUser(
@Field("name") String name,
@Field("email") String email,
@Field("password") String password,
@Field("mobile") String mobile
    );
     @GET("package")
    Call<SubscriptionPackageResponse> getSubscriptionPackage();

    @FormUrlEncoded
    @POST("user")
    Call<UpdatePackageResponse> subscribeToPackage(
            @Field("_method") String _method,
            @Field("user_id") String user_id,
            @Field("package_id") String package_id

    );
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password


    );
    @GET("banner")
    Call<SliderModelResponse> getSliders();

    @GET("vedios")
    Call<OriginalsModelRespoce> getVedios();





}
