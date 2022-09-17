package com.example.viwiki.domain

import com.squareup.moshi.Json

interface BasePage {
    @Json(name = "pageid")
    val pageId: Int
    val title: String
}

abstract class BasePageImpl : BasePage {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
