package com.example.artventureindonesia.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class TestMLResponse(

	@field:SerializedName("result")
	val result: String
) : Parcelable
