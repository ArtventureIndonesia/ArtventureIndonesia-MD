package com.example.artventureindonesia.remote.api

import com.example.artventureindonesia.remote.response.DetailTaskResponse
import com.example.artventureindonesia.remote.response.LoginResponse
import com.example.artventureindonesia.remote.response.PlaceResponse
import com.example.artventureindonesia.remote.response.RegisterResponse
import com.example.artventureindonesia.remote.response.TaskResponse
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

    @GET("/museum/pohR79Bp9F6GNlV1FEmi/{object_doc}")
    suspend fun getDetailTask(
        @Path("object_doc") objectDoc: String
    ): DetailTaskResponse

}