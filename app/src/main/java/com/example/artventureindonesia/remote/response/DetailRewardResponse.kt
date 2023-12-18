package com.example.artventureindonesia.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class DetailRewardResponse(

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("rewardsData")
	val rewardsData: RewardsData
) : Parcelable

@Parcelize
data class RewardsData(

	@field:SerializedName("reward_name")
	val rewardName: String? = null,

	@field:SerializedName("reward_point")
	val rewardPoint: Int? = null,

	@field:SerializedName("reward_doc")
	val rewardDoc: String? = null,

	@field:SerializedName("url_reward_img")
	val urlRewardImg: String? = null
) : Parcelable
