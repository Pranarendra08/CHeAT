package com.example.cheat.data.remote.retrofit

import com.example.cheat.data.remote.response.PostLoginResponse
import com.example.cheat.data.remote.response.PostRegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("signup")
    fun createUserAccount(
        @Field("username") username : String,
        @Field("password") password : String,
    ) : Call<PostRegisterResponse>

    @FormUrlEncoded
    @POST("login")
    fun loginUserAccount(
        @Field("username") username: String,
        @Field("password") password: String,
    ) : Call<PostLoginResponse>

}