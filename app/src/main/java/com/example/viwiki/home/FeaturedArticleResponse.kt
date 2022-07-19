package com.example.viwiki.home

import com.squareup.moshi.Json

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
        @Json(name = "displaytitle") val displayTitle: String = ""
    )

    data class OnThisDayArticle(
        val text: String = ""
    )


}


