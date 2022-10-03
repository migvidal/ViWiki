package com.example.viwiki.repository.page

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.viwiki.Thumbnail
import com.example.viwiki.domain.BasePage
import com.example.viwiki.domain.page.Page

/**
 * Database entity for the Wikipedia page
 */
@Entity(tableName = DatabasePage.TABLE_NAME)
data class DatabasePage(
    @PrimaryKey @ColumnInfo(name = "page_id")
    override val pageId: Int,
    override val title: String,
    val extract: String,
    @ColumnInfo(name = "normalized_title")
    val normalizedTitle: String,
    @Embedded val thumbnail: DatabaseThumbnail
) : BasePage {

    companion object {
        const val TABLE_NAME = "page_table"
    }
}


/**
 * Database entity for the page thumbnail
 */
@Entity(tableName = Thumbnail.TABLE_NAME)
data class DatabaseThumbnail(
    val source: String,
    val width: Int,
    val height: Int
) {
    companion object {
        const val TABLE_NAME = "thumbnail_table"
    }
}


/**
 * Converts a list of DatabasePage to a list of Page
 */
fun List<DatabasePage>.asDomainModel(): List<Page> {
    return map {
        Page(
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
    }
}