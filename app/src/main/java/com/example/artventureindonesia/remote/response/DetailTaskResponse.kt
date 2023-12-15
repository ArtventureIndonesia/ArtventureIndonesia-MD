package com.example.artventureindonesia.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class DetailTaskResponse(

	@field:SerializedName("taskData")
	val taskData: TaskData,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
) : Parcelable

@Parcelize
data class TaskData(

	@field:SerializedName("takenBy")
	val takenBy: List<String>,

	@field:SerializedName("object_doc")
	val objectDoc: String,

	@field:SerializedName("object_name")
	val objectName: String,

	@field:SerializedName("object_description")
	val objectDescription: String,

	@field:SerializedName("object_year")
	val objectYear: String,

	@field:SerializedName("object_id")
	val objectId: Int,

	@field:SerializedName("points")
	val points: Int
) : Parcelable
