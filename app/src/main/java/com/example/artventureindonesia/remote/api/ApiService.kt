package com.example.artventureindonesia.remote.api

import com.example.artventureindonesia.remote.response.TestMLResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @Multipart
    @POST("predicts")
    fun uploadImage(
        @Part file: MultipartBody.Part,
    ): Call<TestMLResponse>

}