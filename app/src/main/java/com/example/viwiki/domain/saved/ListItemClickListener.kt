package com.example.viwiki.domain.saved

/**
 * Click listener to use in a page adapter
 * @param callback function to execute on click
 */
class ListItemClickListener(private val callback: (pageTitle: String) -> Unit) {
    /**
     * Calls the callback function
     * @param pageTitle The page title
     */
    fun onClick(pageTitle: String) {
        return callback(pageTitle)
    }
}