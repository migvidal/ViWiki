package com.example.viwiki

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.view.children
import androidx.databinding.BindingAdapter
import coil.load
import com.example.viwiki.network.ApiUtils
import com.example.viwiki.network.ApiUtils.ApiStatus.*

@BindingAdapter("htmlText")
fun bindButton(tv: TextView, htmlText: String?) {
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
        imageView.load(it) {
            placeholder(android.R.color.darker_gray)
            error(R.drawable.ic_round_broken_image_24)
        }
    }
}

@BindingAdapter("status")
fun bindMessageViewGroup(viewGroup: ViewGroup, status: ApiUtils.ApiStatus?) {
    when (status) {
        LOADING -> viewGroup.visibility = View.VISIBLE
        ERROR -> viewGroup.visibility = View.VISIBLE
        else  -> viewGroup.visibility = View.GONE
    }
}
@BindingAdapter("status")
fun bindButton(button: Button, status: ApiUtils.ApiStatus?) {
    when (status) {
        ERROR -> button.visibility = View.VISIBLE
        else  -> button.visibility = View.INVISIBLE
    }
}
