package com.example.artventureindonesia.remote.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.artventureindonesia.remote.api.ApiConfig
import com.example.artventureindonesia.remote.api.ApiService
import com.example.artventureindonesia.remote.response.TestMLResponse
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class Repository private constructor(
    private val context: Context,
    private val apiServices: ApiService,
//    private val preference: UserPreference,
) {
//
////    private val _isLoading = MutableLiveData<Boolean>()
////    val isLoading: LiveData<Boolean> = _isLoading
////
////    private val _snackBarText = MutableLiveData<Event<String>>()
////    val snackBarText: LiveData<Event<String>> = _snackBarText
//
//    fun uploadImage(imageFile: File, description: String): LiveData<TestMLResponse> {
////        _isLoading.value = true
//        val detail = MutableLiveData<TestMLResponse>()
//        val requestBody = description.toRequestBody("text/plain".toMediaType())
//        val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
//        val multipartBody = MultipartBody.Part.createFormData(
//            "photo",
//            imageFile.name,
//            requestImageFile
//        )
////        val user = runBlocking { preference.getSession().first() }
//        val uploadImageResponse = ApiConfig.getApiService().uploadImage(multipartBody, requestBody)
//
//        uploadImageResponse.enqueue(object : Callback<TestMLResponse> {
//            override fun onResponse(
//                call: Call<TestMLResponse>,
//                response: Response<TestMLResponse>
//            ) {
//
//            }
//
////            override fun onFailure(call: Call<TestMLResponse>, t: Throwable) {
////                _isLoading.value = false
////                _snackBarText.value = Event("${t.message}")
////            }
//        })
//
//        return detail
    }
