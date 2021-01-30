package ru.maxultra.developerslife.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://developerslife.ru"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface GifApiService {
    @GET("random?json=true")
    suspend fun getRandomImage(): NetworkGif
}

object GifApi {
    val retrofitService: GifApiService by lazy {
        retrofit.create(GifApiService::class.java)
    }
}
