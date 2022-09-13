package com.example.viwiki.repository.page

import android.content.Context
import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import coil.request.ImageRequest
import com.example.viwiki.WikipediaApiImpl
import com.example.viwiki.domain.page.Page
import java.io.File
import java.io.FileOutputStream

/**
 * Source of Wikipedia pages
 */
class PageRepositoryImpl(
    private val dao: PageDatabaseDao,
    private val api: WikipediaApiImpl,
    private val context: Context
) : PageRepository {

    /**
     * Whether the Page is saved locally or not
     */
    var savedLocally = false

    /**
     * Loads the page from the appropriate data source
     * @param title The exact title of the page
     * @return live data with the Page
     */
    override suspend fun getPage(title: String): Page {
        // A - From the database
        dao.getPageByTitle(title).also {
            if (it != null) {
                savedLocally = true
                return it
            }
        }
        // B - From the network
        // TODO RENAME OLD "ARTICLE" REFERENCES
        api.wikipediaApiService.getArticleResponse(title).apply {
            return query.pages[0]
        }

    }

    /**
     * Save page locally
     */
    override suspend fun savePage(page: Page) {
        // Save thumbnail in storage
        saveThumbnail(page)
        // Save article in db
        dao.insertPage(page)
    }

    /**
     * Save the page thumbnail
     */
    private fun saveThumbnail(page: Page) {
        // TODO with coroutines
        val thumbnailUrl = page.thumbnail.source
        val request = ImageRequest.Builder(context)
            .data(thumbnailUrl)
            .target(
                onSuccess = { result ->
                    val bitmap = result.toBitmap()
                    val fileName = "${page.pageId}_thumbnail"
                    saveBitmapAsFile(bitmap, fileName)
                }
            )
        request.build()
    }

    /**
     * Save bitmap into file system
     */
    private fun saveBitmapAsFile(bitmap: Bitmap, fileName: String) {
        val file = File(context.filesDir, fileName)
        FileOutputStream(file).apply {
            try {
                bitmap.compress(
                    Bitmap.CompressFormat.WEBP_LOSSLESS,
                    100,
                    this
                )
                close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}