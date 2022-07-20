package com.example.viwiki.home

import com.squareup.moshi.Json
import java.security.CodeSource

/**
 * Featured article response from given date
 */

data class FeaturedArticleResponse(
    val tfa: Tfa = Tfa(),
    @Json(name = "onthisday") val onThisDay: List<OnThisDayArticle> = listOf(OnThisDayArticle())
) {
    data class Tfa(
        val type: String = "",
        val title: String = "",
        @Json(name = "displaytitle") val displayTitle: String = "",
        val thumbnail: Thumbnail = Thumbnail()
    ) {
        data class Thumbnail (
            val source: String = "",
            val width: Int = 0,
            val height: Int = 0
        )
    }

    data class OnThisDayArticle(
        val text: String = ""
    )


}


