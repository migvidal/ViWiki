package com.example.viwiki

import android.content.Intent
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.viwiki.GenericWikiViewModel.ResponseStatus.*
import com.example.viwiki.MainActivity.Companion.ARTICLE_TITLE_EXTRA_KEY
import com.example.viwiki.search.SearchResponse

/**
 * Loads the url into the imageView
 */
@BindingAdapter("imageUrl")
fun bindImageView(imageView: ImageView, imageUrl: String?) {
    imageUrl.let {
        imageView.load(it) {
            error(R.drawable.ic_broken_image)
        }
    }
}


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
    val endOfSentencePattern = "[.](?=[\\s\n\r][A-Z])" // E.g.: First sentence(. S)econd sentence
    val splitString = extract.split(Regex(endOfSentencePattern), 2)
    // Set textView text
    textView.apply {
        text = when (id) {
            // For definition
            R.id.tv_definition -> splitString.first()
            // For everything else
            else -> splitString.last()
        }
    }
}

@BindingAdapter("android:onClick")
fun FrameLayout.onClick(searchResult: SearchResponse.SearchQuery.Search) {
    setOnClickListener {
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra(ARTICLE_TITLE_EXTRA_KEY, searchResult.title)
        context.startActivity(intent)
    }
}