package com.example.viwiki.network

import com.example.viwiki.domain.BasePage
import com.squareup.moshi.Json

/**
 * DTO for the Wikipedia page
 */

data class NetworkPage(
    @Json(name = "pageid")
    override val pageId: Int,
    override val title: String,
    val extract: String,
    @Json(name = "normalizedtitle")
    val normalizedTitle: String,
    val thumbnail: NetworkThumbnail
) : BasePage

/**
 * DTO for the page thumbnail
 */
data class NetworkThumbnail(
    val source: String,
    val width: Int,
    val height: Int
)