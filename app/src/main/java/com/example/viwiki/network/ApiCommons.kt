package com.example.viwiki.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.logging.HttpLoggingInterceptor

object ApiCommons {

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

