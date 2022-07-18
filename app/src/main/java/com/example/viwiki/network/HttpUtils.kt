package com.example.viwiki.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient

object HttpUtils {
    /**
     * Converter from Kotlin to JSON
     */
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    /**
     * HTTP client
     */
    val okHttpClient = OkHttpClient.Builder().build()
}

