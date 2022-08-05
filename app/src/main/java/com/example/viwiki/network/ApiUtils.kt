package com.example.viwiki.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.logging.HttpLoggingInterceptor

object ApiUtils {
    /**
     * Values for the status of the response
     */
    enum class ApiStatus { LOADING, ERROR, DONE, BLANK }

    /**
     * Converter from Kotlin to JSON
     */
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    /**
     * Logs the request URL to console
     */
    val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BASIC)

}

