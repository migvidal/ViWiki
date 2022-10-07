package com.example.viwiki.domain.page

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.viwiki.Thumbnail
import com.example.viwiki.domain.BasePage
import com.example.viwiki.repository.page.DatabasePage
import com.squareup.moshi.Json

/**
 * The Wikipedia page
 */
@Entity(tableName = Page.TABLE_NAME)
data class Page(
    @PrimaryKey @ColumnInfo(name = "page_id")
    @Json(name = "pageid")
    override val pageId: Int = 0,
    override val title: String = "",
    val extract: String = "",

    @ColumnInfo(name = "normalized_title")
    @Json(name = "normalizedtitle")
    val normalizedTitle: String = "",
    @Embedded val thumbnail: Thumbnail = Thumbnail()
) : BasePage {

    fun asDatabaseModel(): DatabasePage {
        return DatabasePage(
            pageId = this.pageId,
            title = this.title,
            extract = this.extract,
            normalizedTitle = this.normalizedTitle,
            thumbnail = Thumbnail(
                source = this.thumbnail.source,
                width = this.thumbnail.width,
                height = this.thumbnail.height
            )
        )
    }

    companion object {
        const val TABLE_NAME = "page_table"
    }
}