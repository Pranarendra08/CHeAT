package com.example.cheat.data.remote.retrofit

import com.example.cheat.data.remote.response.PostChatbotResponse
import com.example.cheat.data.remote.response.GetRecipeDetailResponse
import com.example.cheat.data.remote.response.PostLoginResponse
import com.example.cheat.data.remote.response.PostRegisterResponse
import com.example.cheat.data.remote.response.PredictionsItem
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @FormUrlEncoded
    @POST("signup")
    fun createUserAccount(
        @Field("username") username: String,
        @Field("password") password: String,
    ): Call<PostRegisterResponse>

    @FormUrlEncoded
    @POST("login")
    fun loginUserAccount(
        @Field("username") username: String,
        @Field("password") password: String,
    ): Call<PostLoginResponse>

    @GET("recipes/{id}")
    fun getRecipeDetail(
        @Path("id") id: String
    ): Call<GetRecipeDetailResponse>

    @FormUrlEncoded
    @POST("chatbot")
    fun chatbot(
        @Field("messages") messages: String
    ) : Call<PostChatbotResponse>
}