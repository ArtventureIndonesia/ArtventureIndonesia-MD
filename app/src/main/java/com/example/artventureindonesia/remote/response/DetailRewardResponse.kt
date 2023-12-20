package com.example.artventureindonesia.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class DetailRewardResponse(

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("rewardData")
	val rewardData: RewardData
) : Parcelable

@Parcelize
data class RewardData(

	@field:SerializedName("reward_name")
	val rewardName: String,

	@field:SerializedName("reward_point")
	val rewardPoint: Int,

	@field:SerializedName("reward_id")
	val rewardId: Int,

	@field:SerializedName("reward_doc")
	val rewardDoc: String,

	@field:SerializedName("url_reward_img")
	val urlRewardImg: String
) : Parcelable
