package com.example.artventureindonesia.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class PlaceResponse(

	@field:SerializedName("museumData")
	val museumData: List<MuseumDataItem?>? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class MuseumDataItem(

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("isOpen")
	val isOpen: Boolean? = null,

	@field:SerializedName("museum_name")
	val museumName: String? = null,

	@field:SerializedName("url_museum_img")
	val urlMuseumImg: String? = null,

	@field:SerializedName("museum_doc")
	val museumDoc: String? = null,

	@field:SerializedName("location")
	val location: Location? = null,

	@field:SerializedName("museum_id")
	val museumId: Int? = null
) : Parcelable

@Parcelize
data class Location(

	@field:SerializedName("_longitude")
	val longitude: Double? = null,

	@field:SerializedName("_latitude")
	val latitude: Double? = null
) : Parcelable
