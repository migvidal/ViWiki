package com.example.viwiki.home

import com.example.viwiki.Thumbnail
import com.squareup.moshi.Json

/**
 * Featured article response from given date
 */
data class ArticlesOfTheDayResponse(
    val tfa: Article = Article(),
    @Json(name = "onthisday") val onThisDay: List<OnThisDayResponse> = listOf(OnThisDayResponse())
) {

    /**
     * The "on this day" response
     */
    data class OnThisDayResponse(
        val text: String = "",
        val pages: List<Article> = listOf(Article())
    )

    /**
     * An article
     */
    data class Article(
        val type: String = "",
        val title: String = "",
        @Json(name = "normalizedtitle") val normalizedTitle: String = "",
        val thumbnail: Thumbnail = Thumbnail(),
        val extract: String = ""
    )

}


