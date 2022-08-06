package com.example.viwiki

import com.example.viwiki.article_detail.ArticleImagesResponse
import com.example.viwiki.article_detail.ArticleResponse
import com.example.viwiki.network.ApiUtils
import com.example.viwiki.search.SearchResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private val WIKIPEDIA_BASE_URL = "https://en.wikipedia.org/"


/**
 * Adds common parameters to the base URL
 */
private val paramsInterceptor = Interceptor { chain ->
    var request = chain.request()
    val newUrl = request.url.newBuilder()
        .addPathSegments("w/api.php")
        .addQueryParameter("action", "query")
        .addQueryParameter("format", "json")
        .build()
    request = request.newBuilder()
        .url(newUrl)
        .build()
    chain.proceed(request)
}

/**
 * HTTP client
 */
private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(paramsInterceptor)
    .addInterceptor(ApiUtils.loggingInterceptor)// TODO remove for final build
    .build()

/**
 * Retrofit client, for creating the API service
 */

private val retrofit = Retrofit.Builder()
    .baseUrl(WIKIPEDIA_BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(ApiUtils.moshi))
    .build()



/**
 * Interface for Retrofit to handle queries
 */
interface WikipediaApiService {
    /**
     * Fetch and return the ArticleResponse
     * @param title The exact title of the article
     */
    @GET("/")
    suspend fun getArticleResponse(
        @Query("titles") title: String,
        @Query("prop") prop: String = "extracts",
        @Query("exsentences") exsentences: Int = 1,
        @Query("explaintext") explaintext: Int = 1,
        @Query("formatversion") formatVersion: Int = 2
    ): ArticleResponse

    /**
     * Fetch and return the images response
     */
    @GET("/")
    suspend fun getImagesResponse(
        @Query("titles") title: String,
        @Query("prop") prop: String = "pageimages",
        @Query("piprop") piprop: String = "thumbnail",
        @Query("pithumbsize") maxThumbnailWidth: Int = 2000,
        @Query("formatversion") formatVersion: Int = 2,
        @Query("pilicense") piLicense: String = "any"
    ): ArticleImagesResponse

    /**
     * Search for the provided query
     * @param query The search query
     */
    @GET("/")
    suspend fun getSearchResults(
        @Query("srsearch") query: String,
        @Query("srlimit") resultLimit: Int = 30,
        @Query("list") list: String = "search"
    ): SearchResponse
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
