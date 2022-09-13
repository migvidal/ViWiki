package com.example.viwiki.domain.page

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.viwiki.Thumbnail
import com.squareup.moshi.Json

/**
 * The Wikipedia page
 */
@Entity(tableName = Page.TABLE_NAME)
class Page(
    @PrimaryKey @ColumnInfo(name = "page_id")
    @Json(name = "pageid")
    val pageId: Int = 0,
    val title: String = "",
    val extract: String = "",

    @ColumnInfo(name = "normalized_title")
    @Json(name = "normalizedtitle")
    val normalizedTitle: String = "",
    @Embedded val thumbnail: Thumbnail = Thumbnail()
) {
    override fun equals(other: Any?): Boolean = other is Page
            && this.pageId == other.pageId

    override fun hashCode(): Int = this.pageId

    companion object {
        const val TABLE_NAME = "page_table"
    }
}