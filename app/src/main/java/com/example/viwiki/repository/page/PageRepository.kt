package com.example.viwiki.repository.page

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.graphics.drawable.toBitmap
import coil.request.ImageRequest
import com.example.viwiki.WikipediaApiImpl
import com.example.viwiki.domain.page.Page
import java.io.File
import java.io.FileOutputStream

/**
 * Source of Wikipedia pages
 */
class PageRepository(
    private val dao: PageDatabaseDao,
    private val api: WikipediaApiImpl,
    private val context: Context


) {

    /**
     * The Wikipedia page
     */
    var page: Page? = null

    /**
     * Loads the page from the appropriate data source
     * @param title The exact title of the page
     */
    suspend fun retrievePage(title: String) {
        page = dao.getPageByTitle(title)
        if (page == null) {
            val response = api.wikipediaApiService.getArticleResponse(title)
            page = response.query.pages[0]
        }
    }

    suspend fun savePage(page: Page) {
        // Get bitmap
        val thumbnailUrl = page.thumbnail.source
        val request = ImageRequest.Builder(context)
            .data(thumbnailUrl)
            .target(
                onSuccess = { result ->
                    val bitmap = result.toBitmap()
                    // Save in file system
                    val filename = "${page.pageId}_thumbnail"
                    val file = File(context.filesDir, filename)
                    FileOutputStream(file).apply {
                        try {
                            bitmap.compress(Bitmap.CompressFormat.WEBP_LOSSLESS, 100, this)
                            close()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            )

        // Save in db
        dao.insertPage(page)


    }


}