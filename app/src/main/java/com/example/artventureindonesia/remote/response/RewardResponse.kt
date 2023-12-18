package com.example.artventureindonesia.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class RewardResponse(

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("rewardsData")
	val rewardsData: List<RewardsDataItem?>? = null
) : Parcelable

@Parcelize
data class RewardsDataItem(

	@field:SerializedName("reward_name")
	val rewardName: String? = null,

	@field:SerializedName("reward_point")
	val rewardPoint: Int? = null,

	@field:SerializedName("url_reward_img")
	val urlRewardImg: String? = null
) : Parcelable
