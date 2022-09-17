package com.example.viwiki

import android.content.Intent
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.viwiki.GenericWikiViewModel.ResponseStatus.*
import com.example.viwiki.MainActivity.Companion.PAGE_TITLE_EXTRA_KEY
import com.example.viwiki.domain.page.Page
import com.example.viwiki.domain.search.SearchResponse
import timber.log.Timber
import java.io.File

/**
 * Loads the url into the imageView
 */
@BindingAdapter("imageSource", "isSaved")
fun bindImageView(imageView: ImageView, page: Page?, isSaved: Boolean?) {
    if (page == null) {
        return
    }
    // Get image source
    val imageSource =
        when (isSaved) {
            true -> {
                val fileName = "${page.pageId}_thumbnail"
                File(imageView.context.applicationContext.filesDir, fileName)
            }
            else -> page.thumbnail.source
        }
    // Load image
    imageView.load(imageSource) {
        error(R.drawable.ic_broken_image)
    }

}

@BindingAdapter("imageUrl")
// FIXME Old adapter, to be soon removed
fun bindImageView(imageView: ImageView, imageUrl: String?) {
    imageUrl.let {
        imageView.load(it) {
            error(R.drawable.ic_broken_image)
        }
    }
}


/*@BindingAdapter("imageSource")
fun bindImageView(imageView: ImageView, imageSource: Long?) {
    val fileName = "${imageSource}_thumbnail"
    val file = File(imageView.context.applicationContext.filesDir, fileName)
    file.let {
        imageView.load(it) {
            error(R.drawable.ic_broken_image)
        }
    }
}*/


/**
 * Sets text of text view depending on status
 */
@BindingAdapter("status")
fun bindTextView(tv: TextView, status: GenericWikiViewModel.ResponseStatus?) {
    when (status) {
        LOADING -> tv.setText(R.string.loading_message)
        BLANK -> tv.setText(R.string.blank_response_message)
        ERROR -> tv.setText(R.string.error_message)
        else -> tv.text = null
    }
}

@BindingAdapter("article_extract")
fun bindTextView(textView: TextView, extract: String?) {
    if (extract == null) return
    // Separate in definition and body
    val endOfSentenceRegex =
        Regex("""[.](?=[\s\n\r].)""") // E.g.: First sentence(. S)econd sentence
    val splitString: List<String> = extract.split(endOfSentenceRegex, 2)
    // Set textView text
    textView.apply {
        text = when (id) {
            // For definition
            R.id.tv_definition -> splitString.first() + '.'
            // For everything else
            else -> {
                try {
                    splitString.last().trim()
                } catch (e: IndexOutOfBoundsException) {
                    // If for some odd reason there was no more text
                    Timber.e(e)
                    ""
                }
            }
        }
    }
}

@BindingAdapter("android:onClick")
fun FrameLayout.onClick(searchResult: SearchResponse.SearchQuery.Search) {
    setOnClickListener {
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra(PAGE_TITLE_EXTRA_KEY, searchResult.title)
        context.startActivity(intent)
    }
}

@BindingAdapter("android:onClick")
fun FrameLayout.onClick(page: Page) {
    setOnClickListener {
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra(PAGE_TITLE_EXTRA_KEY, page.normalizedTitle)
        context.startActivity(intent)
    }
}
