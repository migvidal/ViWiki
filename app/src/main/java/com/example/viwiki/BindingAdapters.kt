package com.example.viwiki

import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("htmlText")
fun bindTextView(tv: TextView, htmlText: String?) {
    if (htmlText !== null) {
        val textWithoutTags = HtmlCompat
            .fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_COMPACT)
            .toString()
        tv.text = textWithoutTags
    }
}

@BindingAdapter("imageUrl")
fun bindImageView(imageView: ImageView, imageUrl: String?) {
    imageUrl.let {
        imageView.load(it)
    }
    // placeholder
    // error
}
