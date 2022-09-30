package com.example.viwiki.repository.page

import android.content.Context
import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import coil.imageLoader
import coil.request.ImageRequest
import com.example.viwiki.domain.page.Page
import com.example.viwiki.network.WikipediaApiImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream

/**
 * Source of Wikipedia pages
 */
class PageRepositoryImpl(
    private val dao: PageDatabaseDao,
    private val context: Context
) : PageRepository {

    /**
     * FIXME CAN'T BE A MEMBER!!!!
     */
    private val _savedLocally = MutableLiveData<Boolean>()
    val savedLocally: LiveData<Boolean>
        get() = _savedLocally

    /**
     * Loads the page from the appropriate data source
     * @param title The exact title of the page
     * @return the page
     */
    override suspend fun getPage(title: String): Page {
        // A - From the database
        dao.getPageByTitle(title).also {
            if (it != null) {
                _savedLocally.value = true
                return it
            }
        }
        // B - From the network
        _savedLocally.value = false
        // TODO RENAME OLD "ARTICLE" REFERENCES
        WikipediaApiImpl.wikipediaApiService.getPageResponse(title).apply {
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
        dao.insertAll(page)
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
                onError = { error ->
                    Timber.i("img_error!")
                },
                onSuccess = { result ->

                    val bitmap = result.toBitmap()
                    val fileName = "${page.pageId}_thumbnail"
                    saveBitmapAsFile(bitmap, fileName)
                }
            )
        request.build().let {
            context.imageLoader.enqueue(it)
        }
    }

    /**
     * Save bitmap into file system
     */
    private fun saveBitmapAsFile(bitmap: Bitmap, fileName: String) {
        val file = File(context.filesDir, fileName)
        FileOutputStream(file).apply {
            try {
                bitmap.compress(Bitmap.CompressFormat.WEBP_LOSSLESS, 100, this)
                close()
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    /**
     * Deletes a saved page and its data
     */
    override suspend fun deletePage(page: Page): Boolean {
        val fileName = "${page.pageId}_thumbnail"
        try {
            val deletionSuccessful = File(context.filesDir, fileName).delete()
            if (deletionSuccessful) {
                dao.deletePage(page)
                _savedLocally.value = false
            }
            return deletionSuccessful
        } catch (se: SecurityException) {
            Timber.e(se)
            return false
        }
    }

    override suspend fun getAllPages(): List<Page> {
        return withContext(Dispatchers.IO) {
            dao.getAllPages()
        }
    }

}