package com.example.viwiki.network

import com.example.viwiki.network.PageResponse
import com.example.viwiki.domain.search.SearchResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Base URL for the Wikipedia API
 */
private const val WIKIPEDIA_BASE_URL = "https://en.wikipedia.org/"


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
    .addInterceptor(ApiCommons.loggingInterceptor) // TODO: remove in production
    .build()

/**
 * Retrofit client, for creating the API service
 */
private val retrofit = Retrofit.Builder()
    .baseUrl(WIKIPEDIA_BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(ApiCommons.moshi))
    .build()


/**
 * Interface for Retrofit to handle queries
 */
interface WikipediaApiService {
    /**
     * Fetch and return the PageResponse
     * @param title The exact title of the page
     * @return the page response
     */
    @GET("/")
    suspend fun getPageResponse(
        @Query("titles") title: String,
        @Query("prop") prop: String = "extracts|pageimages",
        @Query("exsentences") exSentences: Int = 10,
        @Query("explaintext") exPlainText: Int = 1,

        @Query("piprop") piprop: String = "thumbnail",
        @Query("pithumbsize") maxThumbnailWidth: Int = 2000,
        @Query("pilicense") piLicense: String = "any",
        @Query("formatversion") formatVersion: Int = 2
    ): PageResponse

    /**
     * Search for the provided query
     * @param query The search query
     * @param resultLimit The max number of results fetched
     */
    @GET("/")
    suspend fun getSearchResults(
        @Query("srsearch") query: String,
        @Query("srlimit") resultLimit: Int = 30,
        @Query("list") list: String = "search"
    ): SearchResponse
}

/**
 * Implementation of the interface
 */
object WikipediaApiImpl {
    /**
     * Service for the Wikipedia API
     */
    val wikipediaApiService: WikipediaApiService by lazy {
        retrofit.create(WikipediaApiService::class.java)
    }
}
