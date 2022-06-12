package com.bangkit.eurica.network

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("readData")
    fun getListUsers(): Call<UserResponse>

    @FormUrlEncoded
    @POST("registrasi")
    fun registerUser(
        @Field("name") name: String,
        @Field("password") password: String,
        @Field("confirm_password") confirm_password: String
    ): Call<RegisterResponse>

    @GET("readData")
    fun getMoneyList(): Call<MoneyResponse>
//
//    @GET("{id}")
//    fun getMoneyDetail(
//        @Path("id") id: String
//    ): Call<MoneyDetailResponse>
}

interface MoneyApiService {
    @GET("readData")
    fun getMoneyList(): Call<MoneyResponse>
}

interface ItemApiService {
    @GET("readData")
    fun getItemList(): Call<ItemResponse>
}