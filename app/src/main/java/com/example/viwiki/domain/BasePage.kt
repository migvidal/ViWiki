package com.example.viwiki.domain

import com.squareup.moshi.Json

/**
 * Interface for a basic Wikipedia information entity
 */
interface BasePage {
    @Json(name = "pageid")
    val pageId: Int
    val title: String
    override fun equals(other: Any?): Boolean
    override fun hashCode(): Int
}
