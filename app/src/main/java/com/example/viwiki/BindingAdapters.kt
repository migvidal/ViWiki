package com.example.viwiki

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.viwiki.GenericWikiViewModel.ResponseStatus.*
import timber.log.Timber

/**
 * Strips HTML tags and sets the text to the textView
 */
/*@BindingAdapter("htmlText")
fun bindButton(tv: TextView, htmlText: String?) {
    if (htmlText !== null) {
        val textWithoutTags = HtmlCompat
            .fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_COMPACT)
            .toString()
        tv.text = textWithoutTags
    }
}*/

/**
 * Loads the url into the imageView
 */
@BindingAdapter("imageUrl")
fun bindImageView(imageView: ImageView, imageUrl: String?) {
    imageUrl.let {
        imageView.load(it) {
            placeholder(android.R.color.darker_gray)
            error(R.drawable.ic_round_broken_image_24)
            listener(onError = { _, error ->
                Timber.i("Url: ", imageUrl)
                error.throwable.printStackTrace()
            })
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