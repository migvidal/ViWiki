package com.example.viwiki.search

import com.squareup.moshi.Json

/**
 * Search response from a given query
 */
data class SearchResponse(
    @Json(name = "batchcomplete") val batchComplete: String = "",
    val query: SearchQuery = SearchQuery()
) {
    data class SearchQuery(
        @Json(name = "searchinfo") val searchInfo: SearchInfo = SearchInfo(),
        var search: List<Search> = listOf(Search())
    ) {
        data class SearchInfo(
            /**
             * Number of results found. Zero if nothing was found
             */
            @Json(name = "totalhits") val totalHits: Int = 0
        )

        data class Search(
            val ns: Int = 0,
            val title: String = "",
            @Json(name = "pageid") val pageId: Int = 0,
            val size: Int = 0,
            @Json(name = "wordcount") val wordCount: Int = 0,
            val snippet: String = "",
            @Json(name = "timestamp") val timeStamp: String = ""
        )

        /**
         * Filters out all the results that are disambiguation articles
         */
        fun removeDisambiguationResults() {
            search = search.filterNot {
                it.title.contains("(disambiguation)")
            }
        }
    }

}





