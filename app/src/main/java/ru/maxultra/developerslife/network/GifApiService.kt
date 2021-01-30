package ru.maxultra.developerslife.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "http://developerslife.ru"
private const val JSON_TRUE = "?json=true"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface GifApiService {
    @GET("random$JSON_TRUE")
    suspend fun getRandomImage(): NetworkGif

    @GET("{section}/{page}$JSON_TRUE")
    suspend fun getSectionImages(
        @Path("section") section: String,
        @Path("page") page: Int
    ): ResponseGifArray
}

object GifApi {
    val retrofitService: GifApiService by lazy {
        retrofit.create(GifApiService::class.java)
    }
}

enum class GifSection(val alias: String) { RANDOM(""), LATEST("latest"), TOP("top") }
