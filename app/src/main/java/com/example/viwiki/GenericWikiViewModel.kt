package com.example.viwiki

import androidx.lifecycle.LiveData

interface GenericWikiViewModel {
    /**
     * Status of the response
     */
    val status: LiveData<ResponseStatus>

    /**
     * Values for the status of the response
     */
    enum class ResponseStatus { LOADING, ERROR, DONE, BLANK }
}