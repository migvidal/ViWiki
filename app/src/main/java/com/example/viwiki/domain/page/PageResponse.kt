package com.example.viwiki.domain.page

/**
 * DatabasePage response from given title
 */
data class PageResponse(
    val query: Query = Query()
) {
    /**
     * The query and its response
     */
    data class Query(
        val pages: List<Page> = listOf(Page())
    )
}
