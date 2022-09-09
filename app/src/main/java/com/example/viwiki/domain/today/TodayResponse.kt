package com.example.viwiki.domain.today

import com.example.viwiki.Thumbnail
import com.squareup.moshi.Json

/**
 * Featured article response from given date
 */
data class TodayResponse(
    val tfa: Article = Article(),
    @Json(name = "onthisday") val onThisDay: List<OnThisDay> = listOf(OnThisDay())
) {

    /**
     * The "on this day" response
     */
    data class OnThisDay(
        val text: String = "",
        val pages: List<Article> = listOf(Article()),
        val year: Int = 0
    )

    /**
     * An article
     */
    data class Article(
        @Json(name = "pageid") val pageId: Int = 0,
        val type: String = "",
        val title: String = "",
        @Json(name = "normalizedtitle") val normalizedTitle: String = "",
        val thumbnail: Thumbnail = Thumbnail(),
        val extract: String = ""
    ) {
        override fun equals(other: Any?): Boolean = other is Article
                && this.pageId == other.pageId

        override fun hashCode(): Int = pageId

    }

}


