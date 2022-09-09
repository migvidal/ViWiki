package com.example.viwiki.domain.page

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

/**
 * The article page
 */
@Entity(tableName = Page.TABLE_NAME)
data class Page(
    @PrimaryKey @ColumnInfo(name = "page_id")
    @Json(name = "pageid")
    val pageId: Long = 0,
    val ns: Long = 0,
    val title: String = "",
    val extract: String = ""
) {
    companion object {
        const val TABLE_NAME = "page_table"
    }
}