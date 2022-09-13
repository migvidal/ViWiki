package com.example.viwiki

import com.example.viwiki.domain.today.TodayResponse
import com.example.viwiki.network.ApiCommons
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * WikiMedia API. Used to get the featured article.
 */

/**
 * Base URL for the Wikimedia API
 */
private val WIKIMEDIA_BASE_URL: String = "https://api.wikimedia.org/"

/**
 * HTTP client
 */
private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(ApiCommons.loggingInterceptor)
    .build()

/**
 * Retrofit client, for creating the API service
 */
private val retrofit = Retrofit.Builder()
    .baseUrl(WIKIMEDIA_BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(ApiCommons.moshi))
    .build()

/**
 * Interface for Retrofit to handle queries
 */
interface WikiMediaApiService {
    @GET(
        "/feed/v1/wikipedia/en/featured/" +
                "{yyyy}/{mm}/{dd}"
    )
    /**
     * Fetches the featured article for a certain date
     * @param yyyy The year
     * @param mm The month
     * @param dd The day
     */
    suspend fun getFeatured(
        @Path("yyyy") yyyy: String,
        @Path("mm") mm: String,
        @Path("dd") dd: String
    ): TodayResponse
}

/**
 * Implementation of the interface.
 */
object WikiMediaApiImpl {
    /**
     * Service for the WikiMedia API
     */
    val wikiMediaApiService: WikiMediaApiService by lazy {
        retrofit.create(WikiMediaApiService::class.java)
    }
}
