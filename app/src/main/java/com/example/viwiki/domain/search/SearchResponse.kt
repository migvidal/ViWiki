package com.example.viwiki.domain.search

import com.example.viwiki.domain.BasePage
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

        /**
         * The search result
         */
        data class Search(
            @Json(name = "pageid") override val pageId: Int = 0,
            override val title: String = "",
            val snippet: String = "",
            @Json(name = "wordcount") val wordCount: Int = 0,
            @Json(name = "timestamp") val timeStamp: String = ""
        ) : BasePage

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





