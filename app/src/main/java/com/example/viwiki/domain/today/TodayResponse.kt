package com.example.viwiki.domain.today

import com.example.viwiki.Thumbnail
import com.example.viwiki.domain.page.Page
import com.squareup.moshi.Json

/**
 * The "featured pages" response
 */
data class TodayResponse(
    /**
     * The main featured page
     */
    val tfa: Page = Page(),
    /**
     * Events that happened on this day in past years
     */
    @Json(name = "onthisday") val onThisDay: List<OnThisDay> = listOf(OnThisDay())
) {

    /**
     * A past "on this day" event
     */
    data class OnThisDay(
        val text: String = "",
        val pages: List<Page> = listOf(Page()),
        val year: Int = 0
    )

}


