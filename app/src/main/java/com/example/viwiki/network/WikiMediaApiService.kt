package com.example.viwiki

import com.example.viwiki.home.FeaturedArticleResponse
import com.example.viwiki.network.HttpUtils
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private val WIKIMEDIA_BASE_URL: String = "https://api.wikimedia.org/"

/**
 * Retrofit client, for creating the API service
 */
private val retrofit = Retrofit.Builder()
    .baseUrl(WIKIMEDIA_BASE_URL)
    .client(HttpUtils.okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(HttpUtils.moshi))
    .build()

/**
 * Interface for Retrofit to handle queries
 */
interface WikiMediaApiService {
    @GET("/feed/v1/wikipedia/en/featured/{yyyy}/{mm}/{dd}")
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
    ): FeaturedArticleResponse
}

/**
 * Implementation for the API. To get featured articles,
 * the WikiMedia API must be used.
 */
object WikiMediaApiImpl {
    /**
     * Service for the Wikimedia API
     */
    val wikiMediaApiService: WikiMediaApiService by lazy {
        retrofit.create(WikiMediaApiService::class.java)
    }
}
