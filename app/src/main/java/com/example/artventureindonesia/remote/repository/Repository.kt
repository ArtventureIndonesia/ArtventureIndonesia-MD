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
import com.example.artventureindonesia.remote.response.MLResponse
import com.example.artventureindonesia.remote.response.MuseumDataItem
import com.example.artventureindonesia.remote.response.NewsDataItem
import com.example.artventureindonesia.remote.response.RegisterResponse
import com.example.artventureindonesia.remote.response.RewardDataItem
import com.example.artventureindonesia.remote.response.TaskDataItem
import com.example.artventureindonesia.remote.response.UserDataItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


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
                    isLogin = true,
                    point = loginResponse.userData.userPoints,
                    name = loginResponse.userData.userName
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
            val placeList = place?.map { it ->
                MuseumDataItem(
                    museumDoc = it?.museumDoc,
                    address = it?.address,
                    museumName = it?.museumName,
                    urlMuseumImg = it?.urlMuseumImg,
                    location = it?.location,
                    museumId = it?.museumId,
                    isOpen = it?.isOpen
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
            emit(Result.Error("Get Task Failed : $errorMessage"))
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


    fun uploadImage(imageFile: File, key: String) = liveData {
        emit(Result.Loading)
        val user = runBlocking { userPreference.getSession().first() }
        val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
        val requestId = user.user_id.toRequestBody("text/plain".toMediaType())
        val multipartBody = MultipartBody.Part.createFormData(
            "imageFile",
            imageFile.name,
            requestImageFile
        )
        try {
            val response = ApiConfig.getApiService(user.user_id)
            val successResponse = response.uploadImage(multipartBody, requestId, key)
            emit(Result.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            Log.d("error", e.toString())
            val errorResponse = Gson().fromJson(errorBody, MLResponse::class.java)
            emit(Result.Error(errorResponse.message ?: "Error tidak diketahui"))
        } catch (e: Exception) {
            emit(Result.Error("Kesalahan jaringan atau server"))
            Log.d("errortak", e.toString())
        }
    }

    fun getReward() = liveData {
        emit(Result.Loading)
        try {
            val user = runBlocking { userPreference.getSession().first() }
            val response = ApiConfig.getApiService(user.user_id)
            val rewardResponse = response.getReward()
            val reward = rewardResponse.rewardData

            val rewardList = reward?.map { it ->
                RewardDataItem(
                    rewardName = it?.rewardName,
                    rewardPoint = it?.rewardPoint,
                    urlRewardImg = it?.urlRewardImg,
                    rewardDoc = it?.rewardDoc,
                    rewardId = it?.rewardId
                )
            }

            if (rewardResponse.error == false) {
                emit(Result.Success(rewardList))
            }
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error("Get Reward failed : $errorMessage"))
            Log.d("reward", e.toString())
        } catch (e: Exception) {
            Log.d("error", e.toString())
            emit(Result.Error("Signal Problem"))
        }
    }

    fun getDetailReward(id: String) = liveData {
        emit(Result.Loading)
        try {
            val user = runBlocking { userPreference.getSession().first() }
            val response = ApiConfig.getApiService(user.user_id)
            val detailRewardResponse =   response.getDetailReward(id)

            if (detailRewardResponse != null){
                emit(Result.Success(detailRewardResponse))
            }


        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error("GetDetailReward Failed : $errorMessage"))
            Log.d("reward", e.toString())

        } catch (e: Exception) {
            Log.d("error", e.toString())
            emit(Result.Error("Signal Problem"))
        }
    }

//    fun getDetailArticel(id: String) = liveData {
//        emit(Result.Loading)
//        try {
//            val user = runBlocking { userPreference.getSession().first() }
//            val response = ApiConfig.getApiService(user.user_id)
//            val detailArticelResponse =   response.getDetailArticel(id)
//
//            if (getDetailArticel != null){
//                emit(Result.Success(getDetailArticel))
//            }
//
//
//        } catch (e: HttpException) {
//            val jsonInString = e.response()?.errorBody()?.string()
//            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
//            val errorMessage = errorBody.message
//            emit(Result.Error("GetDetailReward Failed : $errorMessage"))
//            Log.d("reward", e.toString())
//
//        } catch (e: Exception) {
//            Log.d("error", e.toString())
//            emit(Result.Error("Signal Problem"))
//        }
//    }

    fun getRank() = liveData {
        emit(Result.Loading)
        try {
            val user = runBlocking { userPreference.getSession().first() }
            val response = ApiConfig.getApiService(user.user_id)
            val rankResponse = response.getRank()
            val rank = rankResponse.userData
            val placeList = rank?.map { it ->
                UserDataItem(
                    userPoints = it?.userPoints,
                    userId = it?.userId,
                    userName = it?.userName,
                    userRank = it?.userRank,

                )
            }
            if (rankResponse.error == false) {
                emit(Result.Success(placeList))
            }
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error("Get Task Failed : $errorMessage"))
        } catch (e: Exception) {
            Log.d("error", e.toString())
            emit(Result.Error("Signal Problem"))
        }
    }

    fun getArticel() = liveData {
        emit(Result.Loading)
        try {
            val user = runBlocking { userPreference.getSession().first() }
            val response = ApiConfig.getApiService(user.user_id)
            val articelResponse = response.getArticel()
            val rank = articelResponse.newsData
            val placeList = rank?.map { it ->
                NewsDataItem(
                    newsDate = it?.newsDate,
                    newsDoc = it?.newsDoc,
                    newsAuthor = it?.newsAuthor,
                    urlNewsImg = it?.urlNewsImg,
                    newsTitle = it?.newsTitle,
                    newsText = it?.newsText,
                    newsId = it?.newsId

                    )
            }
            if (articelResponse.error == false) {
                emit(Result.Success(placeList))
            }
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error("Get Task Failed : $errorMessage"))
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
