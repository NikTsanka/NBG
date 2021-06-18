package com.ntsan.exercise_02.data.models


import com.squareup.moshi.Json
import androidx.annotation.Keep

@Keep
data class Item(
    @Json(name = "description")
    val description: String,
    @Json(name = "guid")
    val guid: String,
    @Json(name = "link")
    val link: String,
    @Json(name = "pubDate")
    val pubDate: String,
    @Json(name = "title")
    val title: String
)