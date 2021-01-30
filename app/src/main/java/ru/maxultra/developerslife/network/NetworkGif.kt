package ru.maxultra.developerslife.network

import com.squareup.moshi.Json

class NetworkGif(
    val id: String,
    @Json(name = "gifURL") val url: String,
    @Json(name = "description") val title: String
)
