package com.example.viwiki.article_detail

import com.squareup.moshi.Json

/**
 * Page response from given title
 */
data class ArticleResponse(
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
            val extract: String = ""
        )
    }
}
