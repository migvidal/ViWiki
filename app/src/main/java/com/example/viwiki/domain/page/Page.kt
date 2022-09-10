package com.example.viwiki.domain.page

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.viwiki.Thumbnail
import com.squareup.moshi.Json

/**
 * The article page
 */
@Entity(tableName = Page.TABLE_NAME)
class Page(
    @PrimaryKey @ColumnInfo(name = "page_id")
    @Json(name = "pageid")
    val pageId: Long = 0L,
    val title: String = "",
    val extract: String = "",
    val thumbnail: Thumbnail = Thumbnail()
) {

    companion object {
        const val TABLE_NAME = "page_table"
    }
}