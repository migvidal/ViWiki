package com.example.viwiki.article_detail

/**
 * Article response from given title
 */
data class ArticleResponse(
    val batchComplete: Boolean = false,
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
            val pageId: Long = 0,
            val ns: Long = 0,
            val title: String = "",
            val extract: String = ""
        )
    }
}
