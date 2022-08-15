package com.example.viwiki.search

import com.squareup.moshi.Json

/**
 * Search response from a given query
 */
data class SearchResponse(
    @Json(name = "batchcomplete") val batchComplete: String = "",
    val query: SearchQuery? = SearchQuery()
) {
    data class SearchQuery(
        @Json(name = "searchinfo") val searchInfo: SearchInfo = SearchInfo(),
        val search: List<Search> = listOf(Search())
    ) {
        data class SearchInfo(
            /**
             * Number of results found. Zero if nothing was found
             */
            @Json(name = "totalhits") val totalHits: Int = 0
        )

        data class Search(
            val ns: Int? = null,
            val title: String? = null,
            @Json(name = "pageid") val pageId: Int? = null,
            val size: Int? = null,
            @Json(name = "wordcount") val wordCount: Int? = null,
            val snippet: String? = null,
            @Json(name = "timestamp") val timeStamp: String? = null
        )
    }

}





