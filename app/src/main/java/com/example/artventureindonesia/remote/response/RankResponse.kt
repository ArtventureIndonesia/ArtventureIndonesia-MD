package com.example.artventureindonesia.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class RankResponse(

	@field:SerializedName("userData")
	val userData: List<UserDataItem?>? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class UserDataItem(

	@field:SerializedName("user_points")
	val userPoints: Int? = null,

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("user_name")
	val userName: String? = null,

	@field:SerializedName("user_rank")
	val userRank: Int? = null
) : Parcelable
