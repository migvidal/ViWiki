package com.example.viwiki

import com.example.viwiki.article_detail.ArticleResponse
import com.example.viwiki.network.HttpUtils
import com.example.viwiki.search.SearchResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val WIKIPEDIA_BASE_URL = "https://es.wikipedia.org/"

/**
 * Retrofit client, for creating the API service
 */

private val retrofit = Retrofit.Builder()
    .baseUrl(WIKIPEDIA_BASE_URL)
    .client(HttpUtils.okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(HttpUtils.moshi))
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

    @GET("/w/api.php?action=query&format=json&list=search&srlimit=30")
    /**
     * Searches for the provided query
     * @param query The search query
     */
    suspend fun getSearchResults(@Query("srsearch") query: String): SearchResponse
}

/**
 * Implementation for the API
 */
object WikipediaApiImpl {
    /**
     * Service for the Wikipedia API
     */
    val wikipediaApiService: WikipediaApiService by lazy {
        retrofit.create(WikipediaApiService::class.java)
    }
}
