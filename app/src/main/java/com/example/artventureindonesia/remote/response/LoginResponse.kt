package com.example.artventureindonesia.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class LoginResponse(

	@field:SerializedName("userData")
	val userData: UserData,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
) : Parcelable

@Parcelize
data class UserData(

	@field:SerializedName("user_points")
	val userPoints: Int,

	@field:SerializedName("user_email")
	val userEmail: String,

	@field:SerializedName("user_id")
	val userId: String,

	@field:SerializedName("user_name")
	val userName: String,

	@field:SerializedName("completedTask")
	val completedTask: List<String>,

	@field:SerializedName("user_password")
	val userPass: String
) : Parcelable
