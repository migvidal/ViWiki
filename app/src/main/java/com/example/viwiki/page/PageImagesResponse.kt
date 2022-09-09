package com.example.viwiki.page

import com.example.viwiki.Thumbnail
import com.squareup.moshi.Json

/**
 * Page images response from given title
 */
data class PageImagesResponse(
    val query: Query = Query()
) {
    /**
     * The query and its response
     */
    data class Query(
        val pages: List<Page> = listOf(Page())
    ) {
        /**
         * The article page
         */
        data class Page(
            @Json(name = "pageid") val pageId: Long = 0,
            val ns: Long = 0,
            val title: String = "",
            val thumbnail: Thumbnail = Thumbnail()
        )
    }
}
