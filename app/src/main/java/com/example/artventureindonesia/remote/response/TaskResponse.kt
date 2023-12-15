package com.example.artventureindonesia.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class TaskResponse(

	@field:SerializedName("taskData")
	val taskData: List<TaskDataItem?>? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class TaskDataItem(

	@field:SerializedName("takenBy")
	val takenBy: List<String?>? = null,

	@field:SerializedName("object_doc")
	val objectDoc: String? = null,

	@field:SerializedName("object_name")
	val objectName: String? = null,

	@field:SerializedName("object_description")
	val objectDescription: String? = null,

	@field:SerializedName("object_year")
	val objectYear: String? = null,

	@field:SerializedName("object_id")
	val objectId: Int? = null,

	@field:SerializedName("points")
	val points: Int? = null
) : Parcelable
