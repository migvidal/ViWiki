package com.example.viwiki

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.example.viwiki.network.ApiUtils
import com.example.viwiki.network.ApiUtils.ApiStatus.DONE
import com.example.viwiki.network.ApiUtils.ApiStatus.ERROR

/**
 * Strips HTML tags and sets the text to the textView
 */
@BindingAdapter("htmlText")
fun bindButton(tv: TextView, htmlText: String?) {
    if (htmlText !== null) {
        val textWithoutTags = HtmlCompat
            .fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_COMPACT)
            .toString()
        tv.text = textWithoutTags
    }
}

/**
 * Loads the url into the imageView
 */
@BindingAdapter("imageUrl")
fun bindImageView(imageView: ImageView, imageUrl: String?) {
    imageUrl.let {
        imageView.load(it) {
            placeholder(android.R.color.darker_gray)
            error(R.drawable.ic_round_broken_image_24)
        }
    }
}

/**
 * Modifies a viewGroup depending on status
 */
@BindingAdapter("status")
fun bindMessageViewGroup(viewGroup: ViewGroup, status: ApiUtils.ApiStatus?) {
    if (status == DONE) {
        viewGroup.visibility = View.GONE
    }
}

/**
 * Modifies a button depending on status
 */
@BindingAdapter("status")
fun bindButton(button: Button, status: ApiUtils.ApiStatus?) {
    when (status) {
        ERROR -> button.visibility = View.VISIBLE
        else -> button.visibility = View.INVISIBLE
    }
}
