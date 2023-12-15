package com.example.artventureindonesia.remote.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.artventureindonesia.remote.api.ApiService
import com.google.gson.Gson
import retrofit2.HttpException
import com.example.artventureindonesia.remote.result.Result
import androidx.lifecycle.liveData
import com.example.artventureindonesia.pref.UserModel
import com.example.artventureindonesia.pref.UserPreference
import com.example.artventureindonesia.remote.api.ApiConfig
import com.example.artventureindonesia.remote.response.ErrorResponse
import com.example.artventureindonesia.remote.response.LoginResponse
import com.example.artventureindonesia.remote.response.MuseumDataItem
import com.example.artventureindonesia.remote.response.RegisterResponse
import com.example.artventureindonesia.remote.response.TaskDataItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody



class Repository private constructor(
    private val userPreference: UserPreference,
    private val apiServices: ApiService,
) {

    fun register(
        name: String,
        email: String,
        password: String
    ): LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        val emailInput = email.toRequestBody("text/plain".toMediaType())
        val nameInput = name.toRequestBody("text/plain".toMediaType())
        val passwordInput = password.toRequestBody("text/plain".toMediaType())
        try {
            val registerResponse = apiServices.register(nameInput, emailInput, passwordInput)
            if (registerResponse.error == false) {
                emit(Result.Success(registerResponse))
            } else {
                emit(Result.Error(registerResponse.message ?: "Error"))
            }
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error("Registration Failed: $errorMessage"))
        } catch (e: Exception) {
            emit(Result.Error("Signal Problem"))
        }
    }

    fun login(name: String, email: String, password: String): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val nameLogin = name.toRequestBody("text/plain".toMediaType())
            val emailLogin = email.toRequestBody("text/plain".toMediaType())
            val passwordLogin = password.toRequestBody("text/plain".toMediaType())
            val loginResponse = apiServices.login(nameLogin, emailLogin, passwordLogin)
            if (loginResponse.error == false) {
                val user = UserModel(
                    email = email,
                    user_id = loginResponse.userData.userId,
                    isLogin = true
                )
                ApiConfig.userId = loginResponse.message
                userPreference.saveSession(user)
                emit(Result.Success(loginResponse))
            } else {
                emit(Result.Error(loginResponse.message))
            }
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error("Registration Failed: $errorMessage"))
        } catch (e: Exception) {
            emit(Result.Error("Signal Problem"))
        }
    }

    fun getMuseum() = liveData {
        emit(Result.Loading)
        try {
            val user = runBlocking { userPreference.getSession().first() }
            val response = ApiConfig.getApiService(user.user_id)
            val placeResponse = response.getPlace()
            val place = placeResponse.museumData
            val placeList = place.map { it ->
                MuseumDataItem(
                    address = it.address,
                    museumName = it.museumName,
                    urlMuseumImg = it.urlMuseumImg,
                    location = it.location,
                    museumId = it.museumId
                )
            }
            if (placeResponse.error == false) {
                emit(Result.Success(placeList))
            }
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error("Registration Failed : $errorMessage"))
        } catch (e: Exception) {
            Log.d("error", e.toString())
            emit(Result.Error("Signal Problem"))
        }
    }

    fun getTask() = liveData {
        emit(Result.Loading)
        try {
            val user = runBlocking { userPreference.getSession().first() }
            val response = ApiConfig.getApiService(user.user_id)
            val taskResponse = response.getTask()
            val place = taskResponse.taskData
            val placeList = place?.map { it ->
                TaskDataItem(
                    takenBy = it?.takenBy,
                    objectName = it?.objectName,
                    objectDescription = it?.objectDescription,
                    objectYear = it?.objectYear,
                    objectDoc = it?.objectDoc,
                    objectId = it?.objectId,
                    points = it?.points
                )
            }
            if (taskResponse.error == false) {
                emit(Result.Success(placeList))
            }
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error("Registration Failed : $errorMessage"))
        } catch (e: Exception) {
            Log.d("error", e.toString())
            emit(Result.Error("Signal Problem"))
        }
    }

    fun getDetailTask(key: String) = liveData {
        emit(Result.Loading)
        try {
            val user = runBlocking { userPreference.getSession().first() }
            val response = ApiConfig.getApiService(user.user_id)
            val detailStoryResponse =   response.getDetailTask(key)

            if (detailStoryResponse != null){
                emit(Result.Success(detailStoryResponse))
            }

        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error("GetDetailTask Failed : $errorMessage"))

        } catch (e: Exception) {
            Log.d("error", e.toString())
            emit(Result.Error("Signal Problem"))
        }
    }

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(userPreference, apiService)
            }.also { instance = it }
    }


}
