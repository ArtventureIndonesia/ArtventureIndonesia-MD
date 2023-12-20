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

	@field:SerializedName("rewardData")
	val rewardData: List<RewardDataItem?>? = null
) : Parcelable

@Parcelize
data class RewardDataItem(

	@field:SerializedName("reward_name")
	val rewardName: String? = null,

	@field:SerializedName("reward_point")
	val rewardPoint: Int? = null,

	@field:SerializedName("reward_id")
	val rewardId: Int? = null,

	@field:SerializedName("reward_doc")
	val rewardDoc: String? = null,

	@field:SerializedName("url_reward_img")
	val urlRewardImg: String? = null
) : Parcelable
