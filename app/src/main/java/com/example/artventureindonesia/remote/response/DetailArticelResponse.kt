package com.example.artventureindonesia.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class DetailArticelResponse(

	@field:SerializedName("newsData")
	val newsData: NewsData? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class NewsDatee(

	@field:SerializedName("_nanoseconds")
	val nanoseconds: Int? = null,

	@field:SerializedName("_seconds")
	val seconds: Int? = null
) : Parcelable

@Parcelize
data class NewsData(

	@field:SerializedName("news_date")
	val newsDate: NewsDate? = null,

	@field:SerializedName("news_doc")
	val newsDoc: String? = null,

	@field:SerializedName("news_author")
	val newsAuthor: String? = null,

	@field:SerializedName("url_news_img")
	val urlNewsImg: String? = null,

	@field:SerializedName("news_title")
	val newsTitle: String? = null,

	@field:SerializedName("news_text")
	val newsText: String? = null,

	@field:SerializedName("news_id")
	val newsId: Int? = null
) : Parcelable
