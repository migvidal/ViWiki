package com.example.viwiki.repository.page

import android.content.Context
import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import coil.request.ImageRequest
import com.example.viwiki.WikipediaApiImpl
import com.example.viwiki.domain.page.Page
import java.io.File
import java.io.FileOutputStream
import kotlin.properties.Delegates

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
    var mPage: Page? = null

    /**
     * Whether the Page is saved locally or not
     */
    var mSaved = false

    /**
     * Loads the page from the appropriate data source
     * @param title The exact title of the page
     * @return whether it's saved in db or not
     */
    suspend fun getPage(title: String) {
        // A - From the database
        mPage = dao.getPageByTitle(title).also {
            if (it != null) {
                mSaved = true
                return
            }
        }
        // B - From the network
        // TODO RENAME OLD "ARTICLE" REFERENCES
        api.wikipediaApiService.getArticleResponse(title).apply {
            mPage = query.pages[0]
        }
    }


    suspend fun savePage(page: Page) {
        // Save thumbnail in storage
        saveThumbnail(page)
        // Save article in db
        dao.insertPage(page)
    }

    private fun saveThumbnail(page: Page) {
        // TODO with coroutines
        val thumbnailUrl = page.thumbnail.source
        val request = ImageRequest.Builder(context)
            .data(thumbnailUrl)
            .target(
                onSuccess = { result ->
                    val bitmap = result.toBitmap()
                    saveBitmapAsFile(bitmap)
                }
            )
        request.build()
    }

    private fun saveBitmapAsFile(bitmap: Bitmap) {
        // Save in file system
        val fileName = getThumbnailFileName()
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

    private fun getThumbnailFileName() = "${mPage?.pageId}_thumbnail" // e.g: '124134_thumbnail.webp'

}