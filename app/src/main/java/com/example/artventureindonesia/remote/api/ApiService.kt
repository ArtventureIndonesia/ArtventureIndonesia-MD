package com.example.artventureindonesia.remote.api

import com.example.artventureindonesia.remote.response.ArticelResponse
import com.example.artventureindonesia.remote.response.DetailArticelResponse
import com.example.artventureindonesia.remote.response.DetailRewardResponse
import com.example.artventureindonesia.remote.response.DetailTaskResponse
import com.example.artventureindonesia.remote.response.LoginResponse
import com.example.artventureindonesia.remote.response.MLResponse
import com.example.artventureindonesia.remote.response.PlaceResponse
import com.example.artventureindonesia.remote.response.RankResponse
import com.example.artventureindonesia.remote.response.RegisterResponse
import com.example.artventureindonesia.remote.response.RewardResponse
import com.example.artventureindonesia.remote.response.TaskResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {


    @Multipart
    @POST("users/register")
    suspend fun register(
        @Part("user_name") name: RequestBody,
        @Part("user_email") email: RequestBody,
        @Part("user_password") password: RequestBody
    ): RegisterResponse

    @Multipart
    @POST("users/login")
    suspend fun login(
        @Part("user_name") name: RequestBody,
        @Part("user_email") email: RequestBody,
        @Part("user_password") password: RequestBody
    ): LoginResponse


    @GET("/museum")
    suspend fun getPlace(): PlaceResponse

    @GET("/museum/pohR79Bp9F6GNlV1FEmi")
    suspend fun getTask(): TaskResponse

    @GET("/rewards")
    suspend fun getReward(): RewardResponse


    @GET("/museum/pohR79Bp9F6GNlV1FEmi/{object_doc}")
    suspend fun getDetailTask(
        @Path("object_doc") objectDoc: String
    ): DetailTaskResponse

    @Multipart
    @POST("/predict/pohR79Bp9F6GNlV1FEmi/{object_doc}")
    suspend fun uploadImage(
        @Part imageFile: MultipartBody.Part,
        @Part("user_id") id: RequestBody,
        @Path("object_doc") objectDoc: String
    ): MLResponse

    @GET("/rewards/{reward_doc}")
    suspend fun getDetailReward(
        @Path("reward_doc") rewardDoc: String
    ): DetailRewardResponse

    @GET("/users/rank")
    suspend fun getRank(): RankResponse

    @GET("/news")
    suspend fun getArticel(): ArticelResponse

    @GET("/news/{news_doc}")
    suspend fun getDetailArticel(
        @Path("news_doc") newsDoc: String
    ): DetailArticelResponse



}