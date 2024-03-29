package com.example.viwiki.home

import com.example.viwiki.Thumbnail
import com.squareup.moshi.Json

/**
 * Featured article response from given date
 */
data class FeaturedArticleResponse(
    val tfa: Tfa = Tfa(),
    @Json(name = "onthisday") val onThisDay: List<OnThisDayArticle> = listOf(OnThisDayArticle())
) {
    /**
     * The featured article of the day
     */
    data class Tfa(
        val type: String = "",
        val title: String = "",
        @Json(name = "normalizedtitle") val normalizedTitle: String = "",
        val thumbnail: Thumbnail = Thumbnail(),
        val extract: String = ""
    )

    /**
     * The "on this day" article
     */
    data class OnThisDayArticle(
        val text: String = ""
    )


}


