package com.example.viwiki.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Page.TABLE_NAME)
data class Page(
    @PrimaryKey
    @ColumnInfo(name = "page_id")
    val pageId: Long = 0,
    val ns: Long = 0,
    val title: String = "",
    val extract: String = ""
) {
    companion object {
        const val TABLE_NAME = "page_table"
    }
}

