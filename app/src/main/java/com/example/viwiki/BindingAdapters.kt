package com.example.viwiki

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.viwiki.GenericWikiViewModel.ResponseStatus.*
import com.example.viwiki.article_detail.ArticleResponse

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
fun bindTextView(tv: TextView, extract: String?) {
    val DELIMITER = ". "
    if (tv.id == R.id.tv_definition) {
        tv.text = extract?.substringBefore(DELIMITER) + '.'
        return
    }
    tv.text = extract?.substringAfter(DELIMITER)?.trim()
}
