package ru.maxultra.developerslife.network

import com.squareup.moshi.Json

data class NetworkGif(
    val id: String,
    @Json(name = "gifURL") val url: String,
    @Json(name = "description") val title: String
)

data class ResponseGifArray(
    @Json(name = "result") val gifs: List<NetworkGif>
)
