package com.example.viwiki

import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("htmlText")
fun bindTextView(tv: TextView, html: String) {
    val textWithoutTags = HtmlCompat
        .fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT)
        .toString()
    tv.text = textWithoutTags
}