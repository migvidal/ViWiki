package com.example.viwiki.utils

import android.util.Log

object Logger {
    fun logInfo(tag: String, message: String) {
        Log.i(tag, message)
    }
}