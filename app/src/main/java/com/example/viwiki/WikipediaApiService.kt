package com.example.viwiki

import com.example.viwiki.model.ArticleResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val WIKIPEDIA_BASE_URL = "https://es.wikipedia.org/"


/**
 * Converter from Kotlin to JSON
 */
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

/**
 * HTTP client
 */
private val okHttpClient = OkHttpClient.Builder().build()

/**
 * Retrofit client, for creating the API service
 */
val retrofit = Retrofit.Builder()
    .baseUrl(WIKIPEDIA_BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

/**
 * Interface for Retrofit to handle queries
 */
interface WikipediaApiService {
    @GET(
        "/w/api.php?" +
                "action=query" +
                "&format=json" +
                "&prop=extracts" +
                "&exsentences=1" +
                "&exlimit=1" +
                "&explaintext=1" +
                "&formatversion=2"
    )
    /**
     * Fetches the ArticleResponse
     * @param title The exact title of the article
     */
    suspend fun getArticleResponse(@Query("titles") title: String): ArticleResponse
}

/**
 * Implementation for the API
 */
object ApiImpl {
    /**
     * Service for the Wikipedia API
     */
    val wikipediaApiService: WikipediaApiService by lazy {
        retrofit.create(WikipediaApiService::class.java)
    }
}
