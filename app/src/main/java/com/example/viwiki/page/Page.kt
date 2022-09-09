package com.example.viwiki.page

import com.squareup.moshi.Json

/**
 * The article page
 */
data class Page(
    @Json(name = "pageid") val pageId: Long = 0,
    val ns: Long = 0,
    val title: String = "",
    val extract: String = ""
)