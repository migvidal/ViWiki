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
    @Embedded val thumbnail: Thumbnail
) : BasePage {

    /**
     * Converts a list of DatabasePage to a list of Page
     */
    fun asDomainModel(): Page {
        return Page(
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


