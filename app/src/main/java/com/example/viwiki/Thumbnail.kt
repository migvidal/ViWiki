package com.example.viwiki

import androidx.room.Entity

/**
 * A photo thumbnail for an article
 */
@Entity(tableName = Thumbnail.TABLE_NAME)
data class Thumbnail(
    val source: String = "",
    val width: Int = 0,
    val height: Int = 0
) {
    companion object {
        const val TABLE_NAME = "thumbnail_table"
    }
}
