package com.example.viwiki.network

import com.example.viwiki.Thumbnail
import com.example.viwiki.domain.BasePage
import com.example.viwiki.domain.page.Page
import com.example.viwiki.repository.page.DatabasePage
import com.squareup.moshi.Json


/**
 * Page response for given title.
 * Parses the first level of the network response.
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

/**
 * Converts the PageResponse to a list of DatabasePage models
 */
fun PageResponse.asDatabaseModel(): Array<DatabasePage> {
    return query.pages.map {
        DatabasePage(
            pageId = it.pageId,
            title = it.title,
            extract = it.extract,
            normalizedTitle = it.normalizedTitle,
            thumbnail = Thumbnail(
                source = it.thumbnail.source,
                width = it.thumbnail.width,
                height = it.thumbnail.height
            )
        )
    }.toTypedArray()
}


/**
 * DTO for the Wikipedia page
 */

data class NetworkPage(
    @Json(name = "pageid")
    override val pageId: Int,
    override val title: String,
    val extract: String,
    @Json(name = "normalizedtitle")
    val normalizedTitle: String,
    val thumbnail: NetworkThumbnail
) : BasePage

/**
 * DTO for the page thumbnail
 */
data class NetworkThumbnail(
    val source: String,
    val width: Int,
    val height: Int
)

