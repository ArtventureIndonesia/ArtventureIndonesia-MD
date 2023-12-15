package com.example.artventureindonesia.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class PlaceResponse(

	@field:SerializedName("museumData")
	val museumData: List<MuseumDataItem>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
) : Parcelable

@Parcelize
data class Location(

	@field:SerializedName("_longitude")
	val longitude: Double,

	@field:SerializedName("_latitude")
	val latitude: Double
) : Parcelable

@Parcelize
data class MuseumDataItem(

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("museum_name")
	val museumName: String,

	@field:SerializedName("url_museum_img")
	val urlMuseumImg: String,

	@field:SerializedName("location")
	val location: Location,

	@field:SerializedName("museum_id")
	val museumId: Int,

) : Parcelable
