package com.example.viwiki

import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("htmlText")
fun bindTextView(tv: TextView, htmlText: String?) {
    if (htmlText !== null) {
        val textWithoutTags = HtmlCompat
            .fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_COMPACT)
            .toString()
        tv.text = textWithoutTags

    }
}